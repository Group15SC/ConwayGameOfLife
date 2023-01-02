package main.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid implements Iterable<Cell>{
    final int width;
    final int height;
    Cell [][] grid;

    /**
     * construct a grid filled by cells with fixed size
     * @param width: the width of the grid
     * @param height: the height of the grid
     */
    public Grid(int width, int height){
        this.grid = fillGrid(width, height);
        this.width = width;
        this.height = height;
    }

    /**
     * find a specific cell on grid
     * @param x: x coordinate of the wanted cell
     * @param y: y coordinate of the wanted cell
     * @return Cell
     */
    public Cell getCell(int x, int y){
        return grid[y][x];
    }


    /**
     * set up the grid with blank cells
     * @param width: the size of the grid
     * @param height: the height of the grid
     * @return Cell[][]: the grid of blank cells
     */
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


    /**
     * get the neighbours of a specific cell, number differs, e.g. left corner cell only has 3 neighbours
     * @param center: the cell to find neighbour
     * @return ArrayList<Cell>: the neighbours of the center cell.
     */
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

    /**
     * count the alive neighbours of a center cell
     * @param neighbour: the neighbours of the center cell
     * @return int: number of alive cells among the neighbours
     */
    public int getAliveNeighbours(ArrayList<Cell> neighbour){
        int aliveCount = 0;
        for(Cell cell:neighbour){
            if(cell.getCellStatus()!=CellStatus.BLANK){
                aliveCount++;
            }
        }
        return aliveCount;
    }

    /**
     * get the majority color of neighbour cells of a center cell
     * @param neighbour: the neighbour cells of the center
     * @return CellStatus: the majority color (either RED or BLUE)
     */
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
    public Iterator<Cell> iterator() {
        return new GridIter<>(grid);
    }

}