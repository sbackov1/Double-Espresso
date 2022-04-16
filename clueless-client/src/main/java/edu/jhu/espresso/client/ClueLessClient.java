package edu.jhu.espresso.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.jhu.espresso.client.domain.*;
import edu.jhu.espresso.client.domain.Character;
import edu.jhu.espresso.client.protocol.ProtocolFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class ClueLessClient implements Runnable
{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final ProtocolFactory PROTOCOL_FACTORY = new ProtocolFactory();

    private final String host;
    private final int port;
    private final Socket socket;
    private final PrintWriter printWriter;
    private final BufferedReader bufferedReader;
    private Player player = new Player(0, 0);

    public ClueLessClient(String host, int port)
    {
        player.makeNotebook(new CardDeck());

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
            printWriter.println(OBJECT_MAPPER.writeValueAsString(message));
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
        initializePlayer(gameStart);
        write(gameStart);

        while (true)
        {
            try
            {
                TurnStart turnStart = waitForResponse(TurnStart.class);

                displayGameInfo(turnStart);
                write(turnStart);

                PROTOCOL_FACTORY.determineNextProtocol(
                        turnStart.getClueLessProtocolType(),
                        this
                ).execute(turnStart);
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }
        }
    }

    private void displayGameInfo(TurnStart turnStart) throws JsonProcessingException
    {
        System.out.println(
                OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(
                                turnStart.getLocationNamesMap()
                        )
        );

        System.out.println(player.getNotebook());
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

        CharacterNames playerCharacter = gameStart.getCharacterNames();
        System.out.println("You are playing as " + playerCharacter);
        player.setCharacter(playerCharacter);
    }
}
