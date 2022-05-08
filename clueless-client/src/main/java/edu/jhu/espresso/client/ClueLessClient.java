package edu.jhu.espresso.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jhu.espresso.client.domain.GameEvents.GameStart;
import edu.jhu.espresso.client.domain.GameEvents.TurnStart;
import edu.jhu.espresso.client.domain.GamePieces.*;
import edu.jhu.espresso.client.fx.GameboardController;
import edu.jhu.espresso.client.protocol.ProtocolFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ClueLessClient implements Runnable
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final ProtocolFactory protocolFactory;

    private final String host;
    private final int port;
    private final Socket socket;
    private final PrintWriter printWriter;
    private final BufferedReader bufferedReader;
    private final GameboardController gameboardController;
    private Player player = new Player(0, 0);

    public ClueLessClient(String host, int port, GameboardController gameboardController)
    {
        this.gameboardController = gameboardController;
        player.makeNotebook(new CardDeck());
        gameboardController.setPlayer(player);

        protocolFactory = new ProtocolFactory(gameboardController);

        this.host = host;
        this.port = port;
        try
        {
            socket = new Socket(host, port);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public void write(Object message)
    {
        try
        {
            String messageString = OBJECT_MAPPER.writeValueAsString(message);
            ClientApplication.logMessage("Writing " + messageString + " at " + LocalDateTime.now());
            printWriter.println(messageString);

            //Add a menu kill switch here.

        }
        catch (JsonProcessingException e)
        {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run()
    {
        GameStart gameStart = waitForResponse(GameStart.class);
        gameboardController.setNumberOfPlayers(gameStart.getNumberOfPlayers());
        initializePlayer(gameStart);
        write(gameStart);

        while (true)
        {
            TurnStart turnStart = waitForResponse(TurnStart.class);

            updateCharactersOnBoard(turnStart);
            write(turnStart);

            protocolFactory.determineNextProtocol(
                    turnStart.getClueLessProtocolType(),
                    this
            ).execute(turnStart);
        }
    }

    public void updateCharactersOnBoard(TurnStart turnStart)
    {
        turnStart.getLocationNamesMap().forEach(gameboardController::updateCharacterLocation);
    }

    public <T> T waitForResponse(Class<T> clazz)
    {
        T response = null;
        while (response == null)
        {
            try
            {
                bufferedReader.mark(1_000);
                if (bufferedReader.readLine() != null)
                {
                    bufferedReader.reset();
                    response = OBJECT_MAPPER.readValue(bufferedReader.readLine(), clazz);
                }
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }
        }
        ClientApplication.logMessage("Reading " + response + " at " + LocalDateTime.now());
        return response;
    }

    public <I, O> CompletableFuture<O> write(I input, Class<O> responseClass)
    {
        CompletableFuture<O> response;
        try
        {
            printWriter.println(OBJECT_MAPPER.writeValueAsString(input));
            response = CompletableFuture.supplyAsync(() -> waitForResponse(responseClass));
        }
        catch (JsonProcessingException e)
        {
            throw new IllegalStateException(e);
        }

        return response;
    }

    public Player getPlayer()
    {
        return player;
    }

    private void initializePlayer(GameStart gameStart)
    {
        gameStart.getCharacterNamesList().forEach(
                characterNames -> player.getNotebook().makeHandCard(characterNames.name())
        );

        gameStart.getRoomNamesList().forEach(
                roomNames -> player.getNotebook().makeHandCard(roomNames.name())
        );

        gameStart.getWeapons().forEach(
                weapon -> player.getNotebook().makeHandCard(weapon.name())
        );

        List<String> extraCardsNames = gameStart.getExtraCardsNames();
        extraCardsNames.forEach(name -> player.getNotebook().makeKnownCard(name));

        gameboardController.setExtraCardsNames(extraCardsNames);

        CharacterNames playerCharacter = gameStart.getCharacterNames();
        gameboardController.updateStatusBar("You are playing as " + playerCharacter);
        player.setCharacter(playerCharacter);

        player.setCircle(playerCharacter.getCircleFromBoard(gameboardController));

        gameboardController.updateNotebookObservables();
    }
}
