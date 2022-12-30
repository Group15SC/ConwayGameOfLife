package main.Model;

import java.util.ArrayList;

public class Generation {

    /** update the grid after the generation,
     *  checks whether a user os left with no alive cells*/

    private final Grid grid;
    private static int numberOfGen = 0; // class variable, keep track of the number of generations

    public static int getNumberOfGen() {
        return numberOfGen;
    }

    public Generation(Grid grid) {
        this.grid = grid;
    }

    /** update the whole gird for a generation */
    public void aGeneration(){
        ArrayList<Cell> bringToRed = new ArrayList<>();
        ArrayList<Cell> bringToBlue = new ArrayList<>();
        ArrayList<Cell> overpopulation = new ArrayList<>();
        for(Cell center: grid){
            ArrayList<Cell> neighbour = grid.findNeighbour(center);
            if(center.getCellStatus()==CellStatus.BLANK && bringToLife(center, neighbour)){
                if(grid.getMajorityColor(neighbour)==CellStatus.RED){
                    bringToRed.add(center);
                } else if (grid.getMajorityColor(neighbour) == CellStatus.BLUE) {
                    bringToBlue.add(center);
                }
            } else if (overpopulation(center, neighbour)){
                overpopulation.add(center);
            }
        }
        System.out.println("After a generation");
        for(Cell red: bringToRed){
            grid.getCell(red.getX(), red.getY()).setCellStatus(CellStatus.RED);
        }
        for(Cell blue: bringToBlue){
            grid.getCell(blue.getX(), blue.getY()).setCellStatus(CellStatus.BLUE);
        }
        for(Cell empty: overpopulation){
            grid.getCell(empty.getX(),empty.getY()).setCellStatus(CellStatus.BLANK);
        }
        numberOfGen ++;
    }

    /** bring to life */
    private boolean bringToLife(Cell cell, ArrayList<Cell> neighbour){
        int alive = grid.getAliveNeighbours(neighbour);
        return alive == 3;
    }

    /** overpopulation */
    private boolean overpopulation(Cell cell, ArrayList<Cell> neighbour){
        int alive = grid.getAliveNeighbours(neighbour);
        return alive!=2 && alive!=3;
    }
}
