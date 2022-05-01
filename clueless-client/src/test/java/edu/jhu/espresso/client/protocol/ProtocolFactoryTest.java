package edu.jhu.espresso.client.protocol;

import edu.jhu.espresso.client.ClueLessClient;
import edu.jhu.espresso.client.domain.ClueLessProtocolType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProtocolFactoryTest {

    ClueLessClient client = Mockito.mock(ClueLessClient.class);

    @Test
    void testDetermineNextProtocol() {

        ProtocolFactory pfTest = new ProtocolFactory();

        ClueLessProtocol protocol = pfTest.determineNextProtocol(ClueLessProtocolType.ACTIVE_PLAYER, client);

        assertTrue(protocol instanceof ActivePlayerProtocol);

    }

}