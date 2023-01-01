package main.controller;

import main.controller.mockClasses.MockGame;
import main.controller.mockClasses.MockMessage;
import main.controller.mockClasses.MockUI;
import main.model.Cell;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    MockGame testGame = new MockGame();
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
    void redPlayerKillsABlueCell(){
        // red player first, kill the only blue cell
        assertFalse(controller.getActionKill());
        testGame.getUi().getButtons()[79].doClick();
        assertTrue(controller.getActionKill());
    }

    @Test
    void redPlayerBringARedCellToLife(){
        assertFalse(controller.getActionLife());
        testGame.getUi().getButtons()[0].doClick();
        assertTrue(controller.getActionLife());
    }

    @Test
    void redPlayerWinsTheGame(){
        testGame.getUi().getButtons()[79].doClick();
        testGame.getUi().getButtons()[0].doClick();
        assertEquals(testGame.getGame().getRedPlayer(), controller.getWinner());
    }

    @Test
    void bluePlayerWinsTheGame(){
        mockMessage.setRedPlayerName("CRedPlayer");
        mockMessage.setBluePlayerName("BluePlayer");
        controller.setUpController(mockMessage);

        testGame.getUi().getButtons()[40].doClick();
        testGame.getUi().getButtons()[0].doClick();
        assertEquals(testGame.getGame().getBluePlayer(), controller.getWinner());
    }

}