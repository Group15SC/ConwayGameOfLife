package main.model;

import java.util.ArrayList;

public class Generation {

    /** update the grid after the generation,
     *  checks whether a user os left with no alive cells
     *  */

    private final Grid grid;
    private static int numberOfGen = 0; // class variable, keep track of the number of generations

    /**
     * @return int: number of generations have been made, numberOfGen is a static variable shared by the entire class
     */
    public static int getNumberOfGen() {
        return numberOfGen;
    }

    public Generation(Grid grid) {
        this.grid = grid;
    }

    /**
     * update the whole grid for a generation
     * cells with neighbour number not 2 or 3 would be updated to a dead cell
     * alive cells with 2 or 3 neighbours would stay alive
     * dead cells with 3 neighbours would become alive with the majority color of it's neighbour
     */
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
//        System.out.println("After a generation");
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

    /**
     *  dead cells with 3 neighbours would become alive with the majority color of it's neighbour
     */
    private boolean bringToLife(Cell cell, ArrayList<Cell> neighbour){
        int alive = grid.getAliveNeighbours(neighbour);
        return alive == 3;
    }

    /**
     * cells with neighbour number not 2 or 3 would be updated to a dead cell
     */
    private boolean overpopulation(Cell cell, ArrayList<Cell> neighbour){
        int alive = grid.getAliveNeighbours(neighbour);
        return alive!=2 && alive!=3;
    }

    /**
     * set the number of generation to a specific value(since it is a static value and not changed with instantiation)
     * For testing purpose
     * @param i: the desired generation number (mainly 0)
     */
    public static void setNumberOfGen(int i){
        numberOfGen = i;
    }

}
