package main.controller;

import main.controller.mockClasses.MockGame;
import main.controller.mockClasses.MockMessage;
import main.controller.mockClasses.MockUI;
import main.model.Cell;
import main.model.CellStatus;
import main.model.Generation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.concurrent.Callable;

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

    /** MOCK CASE1: cell(0,1)->red, cell(39,1)->blue,
     *  red player move first, kill the only blue cell and place a red cell at (0,0),
     *  after this turn, red player wins the game */
    @Test
    void redPlayerKillsABlueCell(){
        // red player first, kill the only blue cell
        assertFalse(controller.getActionKill());
        testGame.getUi().getButtons()[79].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        assertTrue(controller.getActionKill());
    }

    @Test
    void redPlayerBringARedCellToLife(){
        assertFalse(controller.getActionLife());
        testGame.getUi().getButtons()[0].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        assertTrue(controller.getActionLife());
    }


    @Test
    void redPlayerWinsTheGame(){
        testGame.getUi().getButtons()[79].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        testGame.getUi().getButtons()[0].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        assertEquals(testGame.getGame().getRedPlayer(), controller.getWinner());
    }

    /** MOCK CASE2: cell(0,1)->red, cell(39,1)->blue,
     *  blue player move first (just adjust the name)
     *  kill the only red cell and place a blue cell at (0,0),
     *  after this turn, blue player wins the game */
    @Test
    void bluePlayerWinsTheGame(){
        mockMessage.setRedPlayerName("CRedPlayer");
        mockMessage.setBluePlayerName("BluePlayer");
        controller.setUpController(mockMessage);

        testGame.getUi().getButtons()[40].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        testGame.getUi().getButtons()[0].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        assertEquals(testGame.getGame().getBluePlayer(), controller.getWinner());
    }

    /** MOCK CASE3: cell(0,1),  (1,1),  (2,1)->red,
     *              cell(37,1), (38,1), (39,1)->blue,
     *  red player move first:
     *  kill blue cell(38,1), place a red cell there
     *  red player's turn ends*/
    @Test
    void redPlayerTurnEnd(){

        testGame.getGame().updateCell(41, CellStatus.RED);
        testGame.getGame().updateCell(42, CellStatus.RED);

        testGame.getGame().updateCell(77, CellStatus.BLUE);
        testGame.getGame().updateCell(78, CellStatus.BLUE);
        mockUI.displayGrid(testGame.getGame().getGrid());

        testGame.getUi().getButtons()[78].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        testGame.getUi().getButtons()[78].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        assertTrue(controller.getTurnEnd());

    }

    /** MOCK CASE4: cell(0,1),  (1,1),  (2,1)->red,
     *              cell(37,1), (38,1), (39,1)->blue,
     *  red player move first: kill blue cell(38,1), place a red cell there
     *  red player's turn ends
     *  blue player move next: kill red cell (42,1), place a blue cell there
     *  blue player's turn ends */
    @Test
    void bluePlayerTurnEnd(){

        testGame.getGame().updateCell(41, CellStatus.RED);
        testGame.getGame().updateCell(42, CellStatus.RED);
        testGame.getGame().updateCell(77, CellStatus.BLUE);
        testGame.getGame().updateCell(78, CellStatus.BLUE);
        mockUI.displayGrid(testGame.getGame().getGrid());

        testGame.getUi().getButtons()[78].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
        testGame.getUi().getButtons()[78].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());

//        for(int i=0; i<mockUI.getButtons().length;i++){
//            if(mockUI.getButtons()[i].getText()=="R"){
//                System.out.println(i);
//            }
//        }
//        System.out.println(mockUI.getButtons()[41].getText());
        testGame.getUi().getButtons()[41].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());
//        System.out.println(mockUI.getButtons()[41].getText());
        testGame.getUi().getButtons()[41].doClick();
        mockUI.displayGrid(testGame.getGame().getGrid());

        assertTrue(controller.getTurnEnd());
    }

    @AfterEach
    void tearDown(){
        Generation.setNumberOfGen(0);
    }

}
