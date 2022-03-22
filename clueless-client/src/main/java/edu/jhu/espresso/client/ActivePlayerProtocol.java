package edu.jhu.espresso.client;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ActivePlayerProtocol
{
    private final MessageStub messageStub;
    private final ClueLessClient client;

    public ActivePlayerProtocol(MessageStub messageStub, ClueLessClient client)
    {
        this.messageStub = messageStub;
        this.client = client;
    }

    public void execute()
    {
        List<String> validMoves = messageStub.getValidMoves();

        Random random = new Random();
        int chosenMoveIndex = Math.abs(random.nextInt() % validMoves.size());

        MessageStub response = new MessageStub();
        response.setValidMoves(Collections.singletonList(validMoves.get(chosenMoveIndex)));
        response.setTurnIndicator(TurnIndicator.ACTIVE_PLAYER);
        response.setHandlerNumber(messageStub.getHandlerNumber());
        client.write(response);
    }
}
