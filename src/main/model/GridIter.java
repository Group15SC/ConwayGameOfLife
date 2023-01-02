package main.model;

import main.model.Cell;
import main.model.Grid;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GridIter<Cell> implements Iterator<Cell> {
    private int currentRow = 0;
    private int currentColumn = 0;
    private Cell[][] grid;

    /**
     * Construct an iterator for the grid,
     * so the cells could be traverse with the same order of the buttons
     */
    public GridIter(Cell[][] grid){
        this.grid = grid;
    }

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
}
