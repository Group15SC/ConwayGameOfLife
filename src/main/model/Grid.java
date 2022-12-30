package main.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Grid implements Iterable<Cell>{
    final int width;
    final int height;
    Cell [][] grid;

    public Grid(int width, int height){
        this.grid = fillGrid(width, height);
        this.width = width;
        this.height = height;
    }
    public Cell getCell(int x, int y){
        return grid[y][x];
    }


    public Cell[][] fillGrid(int width, int height){
        grid = new Cell[height][width];
        for (int y = 0; y < height; y ++){
            for(int x = 0; x < width; x ++){
                grid[y][x]= new Cell(x, y, CellStatus.BLANK);
            }
        }
        return grid;
    }


    public int getWidth(){
        return this.width;
    }

    public int getHeight() {
        return height;
    }


    /** get the 8 neighbours of a cell
     *  input: center cell
     *  output: an array of neighbour cells*/
    public ArrayList<Cell> findNeighbour(Cell center){
        ArrayList<Cell> neighbour = new ArrayList<>();
        int x = center.getX();
        int y = center.getY();
        int[] neighbourX = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] neighbourY = {0, 1, 1, 1, 0, -1, -1, -1};
        for(int i=0; i<8; i++){
            try{
                if(getCell(x+neighbourX[i], y+neighbourY[i])!=null){
                    neighbour.add(getCell(x+neighbourX[i], y+neighbourY[i]));
                };
            } catch (Exception e){
            }
        }
        return neighbour;
    }

    /** count the alive neighbours */
    public int getAliveNeighbours(ArrayList<Cell> neighbour){
        int aliveCount = 0;
        for(Cell cell:neighbour){
            if(cell.getCellStatus()!=CellStatus.BLANK){
                aliveCount++;
            }
        }
        return aliveCount;
    }

    public CellStatus getMajorityColor(ArrayList<Cell> neighbour){
        int red = 0;
        int blue = 0;
        for(Cell cell: neighbour){
            switch (cell.getCellStatus()) {
                case RED -> red++;
                case BLUE -> blue++;
            }
        }
        if(red > blue){
            return CellStatus.RED;
        } else if (blue > red){
            return CellStatus.BLUE;
        }
        return null;
    }

    @Override
    public Iterator<Cell> iterator(){
        return new Iterator<Cell>() {

            private int currentRow = 0;
            private int currentColumn = 0;

            @Override
            public boolean hasNext() {
                if(currentRow + 1 == grid.length){
                    return currentColumn < grid[currentRow].length;
                }
                return currentRow < grid.length;
            }

            @Override
            public Cell next() {
                if (currentColumn == grid[currentRow].length){
                    currentColumn = 0;
                    currentRow ++;
                }
                if(currentRow == grid.length - 1 && currentColumn == grid[currentRow].length){
                    throw new NoSuchElementException();
                }
                return grid[currentRow][currentColumn++];
            }
        };
    }

}