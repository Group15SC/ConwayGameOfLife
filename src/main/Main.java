package main;

import main.board.Cell;
import main.board.CellCollection;
import main.board.CellStatus;
import main.board.Grid;
import main.game.Generation;
import main.player.Player;
import main.ui.Input;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Player testPlayer = new Player("A", "R");
        Grid testGrid = new Grid(5,5);;
        CellCollection testInitial = new CellCollection(testGrid, CellStatus.RED);
        testInitial.addCell(new Cell(0, 3,CellStatus.BLUE));
        testInitial.addCell(new Cell(1,3,CellStatus.RED));
        testInitial.addCell(new Cell(2, 3,CellStatus.BLUE));
//        for(int x=1; x<4; x++){
//            testInitial.addCell(new Cell(x, 3, CellStatus.RED));
//        }
        for(Cell cell: testInitial){
            testGrid.getCell(cell.getX(),cell.getY()).setCellStatus(cell.getCellStatus());
//            testGrid.setCell(cell);
        }
        displayGrid(testGrid);
        ArrayList<Cell> nei = testGrid.findNeighbour(testGrid.getCell(1,3));
        System.out.println("-------------------");
        Generation generation = new Generation(testGrid);
        generation.aGeneration();
        displayGrid(testGrid);
        System.out.println("-------------------");
        generation.aGeneration();
        displayGrid(testGrid);
    }

    private static void displayGrid(Grid testGrid) {
        for(int y = 0; y < testGrid.getHeight(); y++){
            for(int x = 0; x < testGrid.getWidth(); x++) {
                System.out.print(x);
                Cell cell = testGrid.getCell(x,y);
                if(cell.getCellStatus()==CellStatus.RED){
                    System.out.print("R"+"|");
                }
                else if(cell.getCellStatus()==CellStatus.BLUE){
                    System.out.print("B"+"|");
                }
                else{
                    System.out.print(" "+"|");
                }
            }
            System.out.print(y);
            System.out.println();
        }
    }

}