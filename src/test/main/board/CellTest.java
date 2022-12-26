package main.board;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    Cell testCell;

    @BeforeEach
    void setUp(){
        testCell = new Cell(3,4, CellStatus.RED);
    }

    @Test
    void getX() {
        assertEquals(3, testCell.getX());
    }

    @Test
    void getY() {
        assertEquals(4, testCell.getY());
    }

    @Test
    void getCellStatus() {
        assertEquals(CellStatus.RED, testCell.getCellStatus());
    }

//    @Test
//    void getNeighbour() {
//
//    }

    @Test
    void setAnotherStatus() {
        testCell.setCellStatus(CellStatus.BLANK);
        assertEquals(CellStatus.BLANK, testCell.getCellStatus());
    }

}