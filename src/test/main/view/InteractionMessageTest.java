package main.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InteractionMessageTest {

    InteractionMessage message = new InteractionMessage();

    @Test
    void convertR() {
        assertEquals("red", InteractionMessage.colorConverter("R"));
    }

    @Test
    void convertB() {
        assertEquals("blue", InteractionMessage.colorConverter("B"));
    }

    @Test
    void convertOtherInput(){
        assertNull(InteractionMessage.colorConverter("W"));
    }

    @Test
    void setRedPlayerName() {
        // player1 is the red player by default
        message.setRedPlayerName("testRedPlayer");
        assertEquals("testRedPlayer", message.getRedPlayerName());
    }

    @Test
    void setBluePlayerName(){
        message.setBluePlayerName("testBluePlayer");
        assertEquals("testBluePlayer", message.getBluePlayerName());
    }


}