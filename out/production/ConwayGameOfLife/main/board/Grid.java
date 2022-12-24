package model;

import control.Rules;
import javafx.scene.control.Cell;

public class Grid {
    final int width=10000;
    final int height=10000;
    Cell [][] matrix;

    public Grid(){
        fillGrid(width, height);
    }
    public Cell getCell(int x, int y){
        return matrix[x][y];
    }
    public Cell[][] fillGrid(int x, int y){
        matrix = new Cell[width][height];
        for (int row=0; row<x; row++){
            for(int col=0; col<y; col++){
                matrix[row][col]=new Cell(row, col, Cellstatus.Blank )
            }
        }
    }


}