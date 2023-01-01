package main.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model model = new Model();
//    @BeforeAll
//    void setUp(){
//        game.setPlayerName("testPlayerA", "R");
//        game.setPlayerName("testPlayerB", "B");
//    }


    @Test
    void setRedPlayerName() {
        model.setPlayerName("testPlayerA", "R");
        assertEquals("testPlayerA", model.getRedPlayer().getName());
    }

    @Test
    void setBluePlayerName(){
        model.setPlayerName("testPlayerB", "B");
        assertEquals("testPlayerB", model.getBluePlayer().getName());
    }

    @Test
    void testInitialPattern() {
        // Square RED: (14,19), (15,19), (14,20), (15,20)
        assertEquals(CellStatus.RED, model.getGrid().getCell(14, 19).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(15, 19).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(14, 20).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(15, 20).getCellStatus());

        // Square BLUE: (24,19), (25,19), (24,20), (25,20)
        assertEquals(CellStatus.BLUE, model.getGrid().getCell(24, 19).getCellStatus());
        assertEquals(CellStatus.BLUE, model.getGrid().getCell(25, 19).getCellStatus());
        assertEquals(CellStatus.BLUE, model.getGrid().getCell(24, 20).getCellStatus());
        assertEquals(CellStatus.BLUE, model.getGrid().getCell(25, 20).getCellStatus());

    }

    @Test
    void testAEvolve() {
        // square pattern stays the same.
        // RED square: (14,19), (15,19), (14,20), (15,20)
        assertEquals(CellStatus.RED, model.getGrid().getCell(14, 19).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(15, 19).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(14, 20).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(15, 20).getCellStatus());
        model.evolve();
        assertEquals(CellStatus.RED, model.getGrid().getCell(14, 19).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(15, 19).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(14, 20).getCellStatus());
        assertEquals(CellStatus.RED, model.getGrid().getCell(15, 20).getCellStatus());
    }

    @Test
    void testSetACell() {
        model.updateCell(0, CellStatus.BLUE); // -> set the first cell to blue
        assertEquals(CellStatus.BLUE, model.getGrid().getCell(0,0).getCellStatus());
    }

    @Test
    void registerObserver() {

    }

    @Test
    void notifyObserver() {
    }

    @AfterEach
    void tearDown(){
        Generation.setNumberOfGen(0);
    }

}