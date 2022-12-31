package main.controller;

import main.controller.mockClasses.MockAGame;
import main.controller.mockClasses.MockMessage;
import main.controller.mockClasses.MockUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    MockAGame testGame = new MockAGame();
    MockMessage mockMessage = testGame.getMessage();
    Controller controller = testGame.getMockController();
    MockUI mockUI = testGame.getUi();


    @Test
    void testRedPlayerIsFirst(){
        mockMessage.setRedPlayerName("ARedPlayer");
        mockMessage.setBluePlayerName("BluePlayer");
        controller.setUpController(mockMessage);
        assertTrue(controller.getRedTurn());
    }

    @Test
    void testBluePlayerIsFirst(){
        mockMessage.setRedPlayerName("CRedPlayer");
        mockMessage.setBluePlayerName("BluePlayer");
        controller.setUpController(mockMessage);
        assertFalse(controller.getRedTurn());
    }

    @Test
    void killABlueCell(){
        testGame.killOtherBlueCells();
        // first turn is red player's turn
        mockUI.getButtons()[0].doClick(); // kill the only blue cell
        assertTrue(testGame.getMockController().getActionKill());
    }


}