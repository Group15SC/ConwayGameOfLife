package main.board;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    Grid testGrid = new Grid(5,5);
    @BeforeEach
    void setUP(){
        testGrid.getCell(0,0).setCellStatus(CellStatus.RED);
        testGrid.getCell(0,1).setCellStatus(CellStatus.BLUE);
    }

    @Test
    void getCell() {
        Cell testCell = testGrid.getCell(1,1);
        assertEquals(CellStatus.BLANK, testCell.getCellStatus());
    }

    @Test
    void getWidth() {
        assertEquals(5, testGrid.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(5, testGrid.getHeight());
    }

    @Test
    void getNeighbourFromUpperLeftCorner(){
        Cell testCenter = testGrid.getCell(0,0);
        assertEquals(3, testGrid.findNeighbour(testCenter).size()); // upper left corner, only have 3 neighbours
    }

    @Test
    void getNeighbourFromLowerLeftCorner(){
        Cell testCenter = testGrid.getCell(0,testGrid.getHeight()-1);
        assertEquals(3,testGrid.findNeighbour(testCenter).size());
    }

    @Test
    void getNeighbourFromUpperRightCorner(){
        Cell testCenter = testGrid.getCell(testGrid.getWidth()-1,0);
        assertEquals(3, testGrid.findNeighbour(testCenter).size()); // upper left corner, only have 3 neighbours
    }

    @Test
    void getNeighbourFromLowerRightCorner(){
        Cell testCenter = testGrid.getCell(testGrid.getWidth()-1,testGrid.getHeight()-1);
        assertEquals(3,testGrid.findNeighbour(testCenter).size());
    }

    @Test
    void getNeighbourInTheMiddle(){
        Cell testCenter = testGrid.getCell(2,2);
        assertEquals(8,testGrid.findNeighbour(testCenter).size());
    }

    @Test
    void getSomeAliveNeighbour(){
        Cell testCenter = testGrid.getCell(1,0); // should have 3 alive neighbours
        ArrayList<Cell> testNeighbour = testGrid.findNeighbour(testCenter);
        assertEquals(2, testGrid.getAliveNeighbours(testNeighbour));
    }

    @Test
    void getMajorityColorRed(){
        testGrid.getCell(2,0).setCellStatus(CellStatus.RED); // 2 red vs 1 blue -> red is the majority
        Cell testCenter = testGrid.getCell(1,0);
        ArrayList<Cell> testNeighbour = testGrid.findNeighbour(testCenter);
        assertEquals(CellStatus.RED, testGrid.getMajorityColor(testNeighbour));
    }

    @Test
    void getMajorityColorBlue(){
        testGrid.getCell(2,0).setCellStatus(CellStatus.BLUE); // 2 blue vs 1 red -> blue is the majority
        Cell testCenter = testGrid.getCell(1,0);
        ArrayList<Cell> testNeighbour = testGrid.findNeighbour(testCenter);
        assertEquals(CellStatus.BLUE, testGrid.getMajorityColor(testNeighbour));
    }

    @Test
    void sameColor(){
        Cell testCenter = testGrid.getCell(1,0); // 1 blue vs 1 red, -> no majority
        ArrayList<Cell> testNeighbour = testGrid.findNeighbour(testCenter);
        assertNull(testGrid.getMajorityColor(testNeighbour));
    }

    @Test
    void testIterX(){
        ArrayList<Integer> expected = new ArrayList<>();
        for(int i=0; i<25; i++){
            expected.add(i % 5); // repeat 0-4: 0,1,2,3,4,0,1,2,3,4,...
        }
        ArrayList<Integer> actual = new ArrayList<>();
        for(Cell cell:testGrid){
            actual.add(cell.getX());
        }
        assertEquals(expected, actual);
    }

    @Test
    void testIterStatus(){
        ArrayList<CellStatus> expected = new ArrayList<>();
        expected.add(CellStatus.RED);
        for(int i=1; i<25; i++){
            expected.add(CellStatus.BLANK);
        }
        expected.set(5, CellStatus.BLUE);
        ArrayList<CellStatus> actual = new ArrayList<>();
        for(Cell cell: testGrid){
            actual.add(cell.getCellStatus());
        }
        assertEquals(expected, actual);
    }

}