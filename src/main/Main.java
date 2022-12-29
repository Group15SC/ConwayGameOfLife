package main;

import main.board.Cell;
import main.board.CellCollection;
import main.board.CellStatus;
import main.board.Grid;
import main.game.Generation;
import main.player.Player;
import main.ui.GUI;
//import main.ui.Input;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setUp();
        gui.gameOn();
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