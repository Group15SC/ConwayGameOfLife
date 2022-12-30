package main.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game = new Game();
//    @BeforeAll
//    void setUp(){
//        game.setPlayerName("testPlayerA", "R");
//        game.setPlayerName("testPlayerB", "B");
//    }


    @Test
    void setRedPlayerName() {
        game.setPlayerName("testPlayerA", "R");
        assertEquals("testPlayerA", game.getRedPlayer().getName());
    }

    @Test
    void setBluePlayerName(){
        game.setPlayerName("testPlayerB", "B");
        assertEquals("testPlayerB", game.getBluePlayer().getName());
    }

    @Test
    void testInitialPattern() {
        // Square RED: (14,19), (15,19), (14,20), (15,20)
        assertEquals(CellStatus.RED, game.getGrid().getCell(14, 19).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(15, 19).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(14, 20).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(15, 20).getCellStatus());

        // Square BLUE: (24,19), (25,19), (24,20), (25,20)
        assertEquals(CellStatus.BLUE, game.getGrid().getCell(24, 19).getCellStatus());
        assertEquals(CellStatus.BLUE, game.getGrid().getCell(25, 19).getCellStatus());
        assertEquals(CellStatus.BLUE, game.getGrid().getCell(24, 20).getCellStatus());
        assertEquals(CellStatus.BLUE, game.getGrid().getCell(25, 20).getCellStatus());

    }

    @Test
    void testAEvolve() {
        // square pattern stays the same.
        // RED square: (14,19), (15,19), (14,20), (15,20)
        assertEquals(CellStatus.RED, game.getGrid().getCell(14, 19).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(15, 19).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(14, 20).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(15, 20).getCellStatus());
        game.evolve();
        assertEquals(CellStatus.RED, game.getGrid().getCell(14, 19).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(15, 19).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(14, 20).getCellStatus());
        assertEquals(CellStatus.RED, game.getGrid().getCell(15, 20).getCellStatus());
        Generation gen = new Generation(game.getGrid());
        gen.setNumberOfGen(0);
    }

    @Test
    void testSetACell() {
        game.updateCell(0, CellStatus.BLUE); // -> set the first cell to blue
        assertEquals(CellStatus.BLUE, game.getGrid().getCell(0,0).getCellStatus());
    }

    @Test
    void registerObserver() {

    }

    @Test
    void notifyObserver() {
    }
}