package main.player;

import main.board.CellCollection;
import main.board.Grid;
import main.ui.Input;

public class Player {
    private final String name;
    private final String color;

//    private final CellCollection cellCollection;
    private int numberOfCells;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
//        cellCollection = new CellCollection(grid, color);
//        this.numberOfCells = cellCollection.getCellNumber();
        /** number of cells should be a field of a player instance and get changed with each instance's life cycle,
         *  do not need to add it to the parameter of constructor
         *  the exact number should be decided by the initial cell pattern and set in Game class (by setter)
         * */
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

//    public int getNumberOfCells() {
//        return cellCollection.getCellNumber();
//    }

}
