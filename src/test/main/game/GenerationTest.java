package main.game;

import main.board.Cell;
import main.board.CellStatus;
import main.board.Grid;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GenerationTest {

    Grid testGrid = new Grid(5, 5);
    Generation aGen = new Generation(testGrid);

    @BeforeEach
    void setUp() {
        testGrid.getCell(0, 1).setCellStatus(CellStatus.BLUE);
        testGrid.getCell(1, 1).setCellStatus(CellStatus.RED);
        testGrid.getCell(2, 1).setCellStatus(CellStatus.BLUE);
        // (0,1)-> blue, (1,1)-> red, (2,1)-> blue
    }

    @Test
    void beforeGeneration() {
        assertEquals(0, Generation.getNumberOfGen());
    }

    @Test
    void twoGenerations() {
        // after a generation, (1,0)->blue, (1,1)->red, (1,2)->blue
        aGen.aGeneration();
        assertEquals(CellStatus.BLUE, testGrid.getCell(1, 0).getCellStatus());
        assertEquals(CellStatus.RED, testGrid.getCell(1, 1).getCellStatus());
        assertEquals(CellStatus.BLUE, testGrid.getCell(1, 2).getCellStatus());
        assertEquals(1, Generation.getNumberOfGen());

        testGrid.getCell(1,0).setCellStatus(CellStatus.RED); // (1,0)->red, (1,1)->red, (1,2)->blue
        aGen.aGeneration(); //(0,1), (1,1), (2,1) all red
        assertEquals(CellStatus.RED, testGrid.getCell(0, 1).getCellStatus());
        assertEquals(CellStatus.RED, testGrid.getCell(1, 1).getCellStatus());
        assertEquals(CellStatus.RED, testGrid.getCell(2, 1).getCellStatus());
        assertEquals(2, Generation.getNumberOfGen());
    }

}