package main.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class CellCollection implements Iterable<Cell>{

    private final ArrayList<Cell> collection;
    public CellCollection(Grid grid, CellStatus color) {
        this.collection = getCellCollection(grid, color);
    }

    /**
     * find all cells of a specific status/color on a given grid and store in an array list
     * @param grid: given grid
     * @param color: wanted cell color
     * @return ArrayList<Cell>: required cells on the grid
     */
    public ArrayList<Cell> getCellCollection(Grid grid, CellStatus color){
        ArrayList<Cell> collection = new ArrayList<>();
        for(Cell cell: grid){
            if(Objects.equals(cell.getCellStatus(), color)){
                collection.add(cell);
            }
        }
        return collection;
    }

    public int getCellNumber(){
        return this.collection.size();
    }

    @Override
    public Iterator<Cell> iterator() {
        return this.collection.iterator();
    }
}