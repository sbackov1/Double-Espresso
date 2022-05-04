package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.ClueLessProtocolType;
import edu.jhu.espresso.client.fx.GameboardController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProtocolFactoryTest {

    ClueLessClient client = mock(ClueLessClient.class);
    GameboardController gameboardController = mock(GameboardController.class);

    @Test
    void testDetermineNextProtocol() {

        ProtocolFactory pfTest = new ProtocolFactory(gameboardController);

        ClueLessProtocol protocol = pfTest.determineNextProtocol(ClueLessProtocolType.ACTIVE_PLAYER, client);

        assertTrue(protocol instanceof ActivePlayerProtocol);

    }

}