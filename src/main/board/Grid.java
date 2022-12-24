package main.board;

public class Grid {
    final int width = 10000;
    final int height = 10000;
    Cell [][] grid;

    public Grid(){
        grid = fillGrid(width, height);
    }
    public Cell getCell(int x, int y){
        return grid[x][y];
    }
    public Cell[][] fillGrid(int x, int y){
        grid = new Cell[width][height];
        for (int row = 0; row < x; row ++){
            for(int col = 0; col < y; col ++){
                grid[row][col]= new Cell(row, col, CellStatus.BLANK);
            }
        }
        return grid;
    }


}