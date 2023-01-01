package main.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloMessageTest {

    @Test
    void convertR() {
        assertEquals("red", HelloMessage.colorConverter("R"));
    }

    @Test
    void convertB() {
        assertEquals("blue", HelloMessage.colorConverter("B"));
    }

    @Test
    void convertOtherInput(){
        assertNull(HelloMessage.colorConverter("W"));
    }

    @Test
    void handleColor() {
    }

    @Test
    void handleName() {
    }

    @Test
    void getInput() {
    }

    @Test
    void getRedPlayerName() {
    }

    @Test
    void getBluePlayerName() {
    }

    @Test
    void handleSameName() {
    }

    @Test
    void displayWinnerMessage() {
    }
}