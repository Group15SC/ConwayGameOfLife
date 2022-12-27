package main.board;

import main.player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class CellCollection implements Iterable<Cell>{

    /** to store all the cells of a player*/
    private final ArrayList<Cell> collection;
    public CellCollection(Grid grid, CellStatus color) {
        this.collection = getCellCollection(grid, color);
    }
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

//    public void addCell(Cell cell){
//        collection.add(cell);
//    }

    @Override
    public Iterator<Cell> iterator() {
        return this.collection.iterator();
    }
}
