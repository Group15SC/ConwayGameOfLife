package main.board;

import main.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellCollectionTest {

    Grid testGrid = new Grid(5,5);
    Player testPlayer = new Player("testPlayer", "R");
    @BeforeEach
    void setUp() {
        testGrid.getCell(0,0).setCellStatus(CellStatus.RED);
        testGrid.getCell(0,1).setCellStatus(CellStatus.RED);
        testGrid.getCell(0,2).setCellStatus(CellStatus.RED);
    }

    @Test
    void getCellCollection() {
        CellCollection testCollection = new CellCollection(testGrid,CellStatus.RED);
    }

    @Test
    void getCellNumber() {
    }

    @Test
    void addCell() {
    }

    @Test
    void iterator() {
    }
}