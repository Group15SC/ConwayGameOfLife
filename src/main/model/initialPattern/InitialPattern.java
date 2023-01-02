package main.model.initialPattern;

import main.model.Cell;
import main.model.CellStatus;

import java.util.ArrayList;

public abstract class InitialPattern{

    private ArrayList<Cell> pattern;
    private ArrayList<Cell> mirroredPattern;
    private final int[] xPattern, yPattern;


    /**
     * construct an abstract initial patterns
     * @param xPattern: fixed pattern for x coordinate, defined by the type of initial pattern
     * @param yPattern: fixed pattern for y coordinate, defined by the type of initial pattern
     * @param startX: x coordinate of the left corner cell of the position to place the pattern
     * @param startY: y coordinate of the left corner cell of the position to place the pattern
     * @param gridWidth: the width of the grid (to mirror the pattern
     */
    public InitialPattern(int[] xPattern, int[] yPattern, int startX, int startY, int gridWidth) {
        this.xPattern = xPattern;
        this.yPattern = yPattern;
        this.pattern = generatePattern(startX, startY);
        this.mirroredPattern = mirror(gridWidth);
    }

    /**
     * based on the position of the left corner cell, generate the pattern for red cells
     * @param startX: x coordinate of the left corner cell
     * @param startY: y coordinate of the left corner cell
     * @return ArrayList<Cell>: all the cells of the generated pattern
     */
    private ArrayList<Cell> generatePattern(int startX, int startY) {
        pattern = new ArrayList<>();
        for(int i=0; i<xPattern.length; i++){
            pattern.add(new Cell(startX+xPattern[i],startY+yPattern[i], CellStatus.RED));
        }
        return pattern;
    }

    /**
     * mirror the pattern of the red cell, get the pattern of blue cells(left-right), make sure it is symmetric
     * @param gridWidth: the width of the grid
     * @return ArrayList<Cell>: the mirrored pattern with opposite color
     */
    private ArrayList<Cell> mirror(int gridWidth) {
        mirroredPattern = new ArrayList<>();
        for(Cell cell: pattern){
            int targetX = gridWidth - 1 - cell.getX(); // -1: index starts from 0, so for a 40*40 grid, should be 39-x
            mirroredPattern.add(new Cell(targetX, cell.getY(), CellStatus.BLUE));
        }
        return mirroredPattern;
    }

    /**
     * @return Array<Cell>: the generated red cells
     */
    public ArrayList<Cell> getRedPattern(){
        return pattern;
    }

    /**
     * @return ArrayList<Cell>: the generated blue cells
     */
    public ArrayList<Cell> getBluePattern(){
        return mirroredPattern;
    }

}

