package main.view;

import main.model.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class GUITest {
    Game testGame = new Game();
    GUI testGui = new GUI(testGame);

    @Test
    void testUpdateStats() {
        Grid testGrid = new Grid(40,40);
        testGrid.getCell(0,0).setCellStatus(CellStatus.RED);
        testGrid.getCell(0,1).setCellStatus(CellStatus.BLUE);
        testGrid.getCell(1,1).setCellStatus(CellStatus.BLUE);

        testGui.updateStats(testGrid);
        String expect_cell = "   Blue Cells: 2  Red Cells: 1";
        assertEquals(expect_cell, testGui.getCell_info().getText());

        String expect_generation = "Generation:  0   ";
        assertEquals(expect_generation, testGui.getGeneration_info().getText());
    }

    @Test
    void testDisableOtherButtons() {
        Grid testGrid = new Grid(40,40);
        testGrid.getCell(0,0).setCellStatus(CellStatus.BLUE);
        testGrid.getCell(1,0).setCellStatus(CellStatus.BLUE);
        testGrid.getCell(2,0).setCellStatus(CellStatus.BLUE);
        testGrid.getCell(3,0).setCellStatus(CellStatus.BLUE);
        for(JButton button: testGui.getButtons()){
            if(button.getText().equals("B")) {
                assertTrue(button.isEnabled());
            }
        }
        testGui.disableOtherButtons("B");
        for(JButton button: testGui.getButtons()){
            if(button.getText().equals("B")) {
                assertFalse(button.isEnabled());
            }
        }
    }

    @Test
    void testSetButtonFree() {
        Grid testGrid = new Grid(40,40);
        testGrid.getCell(0,0).setCellStatus(CellStatus.BLUE);
        testGrid.getCell(1,0).setCellStatus(CellStatus.BLUE);
        testGrid.getCell(2,0).setCellStatus(CellStatus.BLUE);
        testGrid.getCell(3,0).setCellStatus(CellStatus.BLUE);
        testGui.disableOtherButtons("B");
        for(JButton button: testGui.getButtons()){
            if(button.getText().equals("B")) {
                assertFalse(button.isEnabled());
            }
        }
        testGui.setButtonFree();
        for(JButton button: testGui.getButtons()){
            if(button.getText().equals("B")) {
                assertTrue(button.isEnabled());
            }
        }

    }

    @Test
    void testDisplayGrid() {
        Grid testGrid = new Grid(40,40);
        testGrid.getCell(0,0).setCellStatus(CellStatus.RED);
        testGrid.getCell(1,0).setCellStatus(CellStatus.BLUE);
        testGui.displayGrid(testGrid);
        String actual0 = testGui.getButtons()[0].getText();
        String actual1 = testGui.getButtons()[1].getText();
        assertEquals("R", actual0);
        assertEquals("B", actual1);
    }
}
