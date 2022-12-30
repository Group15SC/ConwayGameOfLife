package main.board;

import java.util.ArrayList;

public abstract class InitialPattern{

    private ArrayList<Cell> pattern;
    private ArrayList<Cell> mirroredPattern;
    private final int[] xPattern, yPattern;


    /** xPattern, yPattern: a fixed pattern, defined by the type of initial pattern
     *  startX, startY: coordinate of the left corner cell of the pattern */
    public InitialPattern(int[] xPattern, int[] yPattern, int startX, int startY, int gridWidth) {
        this.xPattern = xPattern;
        this.yPattern = yPattern;
        this.pattern = generatePattern(startX, startY);
        this.mirroredPattern = mirror(gridWidth);
    }

    private ArrayList<Cell> mirror(int gridWidth) {
        mirroredPattern = new ArrayList<>();
        for(Cell cell: pattern){
            int targetX = gridWidth - 1 - cell.getX(); // -1: index starts from 0, so for a 40*40 grid, should be 39-x
            mirroredPattern.add(new Cell(targetX, cell.getY(), CellStatus.BLUE));
        }
        return mirroredPattern;
    }


    private ArrayList<Cell> generatePattern(int startX, int startY) {
        pattern = new ArrayList<>();
        for(int x: xPattern){
            for(int y: yPattern){
                pattern.add(new Cell(startX+x, startY+y, CellStatus.RED));
            }
        }
        return pattern;
    }

    public ArrayList<Cell> getRedPattern(){
        return pattern;
    }

    public ArrayList<Cell> getBluePattern(){
        return mirroredPattern;
    }

}

