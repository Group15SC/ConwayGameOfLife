package main.model;

import main.model.initialPattern.Boat;
import main.model.initialPattern.InitialPattern;
import main.model.initialPattern.Ship;
import main.model.initialPattern.Square;
import main.view.IObeserver;

import java.util.ArrayList;

public class Model implements ISubject{

    /**
     * build up the game model, take care of the data store and computation
     */

    ArrayList<IObeserver> observers = new ArrayList<>();


    private Grid grid = new Grid(40, 40);
    private final Player player_red, player_blue;

    private Generation generation;

    public Model(){

        setInitialPattern();

        generation = new Generation(grid);
        player_red = new Player("", "R");
        player_blue = new Player("", "B");
    }

    /**
     * set up the initial pattern and update the cells to grid
     */
    public void setInitialPattern() {
        InitialPattern[] patterns = {new Boat(), new Ship(), new Square()};
//        InitialPattern[] patterns = {new Square()};
        for(InitialPattern pattern: patterns){
            for(Cell cell: pattern.getRedPattern()){
                grid.getCell(cell.getX(), cell.getY()).setCellStatus(CellStatus.RED);
            }
            for(Cell cell: pattern.getBluePattern()){
                grid.getCell(cell.getX(), cell.getY()).setCellStatus(CellStatus.BLUE);
            }
        }
    }

    /**
     * @param name: name of the player to be set
     * @param color: color side of the player to be set
     */
    public void setPlayerName(String name, String color){
        if(color.equals("R")){
            player_red.setName(name);
        }
        if(color.equals("B")){
            player_blue.setName(name);
        }
    }

    public Grid getGrid(){
        return grid;
    }


    public Player getRedPlayer(){
        return player_red;
    }

    public Player getBluePlayer(){
        return player_blue;
    }

    /**
     * make a generation and notify observer to update
     */
    public void evolve(){
        generation.aGeneration();
        notifyObserver();
    }

    public CellCollection getRedCells(){
        return new CellCollection(grid, CellStatus.RED);
    }

    public CellCollection getBlueCells(){
        return new CellCollection(grid, CellStatus.BLUE);
    }

    /**
     * update the grid status based on current buttons
     * @param buttonId: the index of the button
     * @param status: the cell status to be set
     */
    public void updateCell(int buttonId, CellStatus status){
        getGrid().getCell(buttonId % 40, buttonId / 40).setCellStatus(status);
        notifyObserver();
    }

    @Override
    public void registerObserver(IObeserver o) {
        observers.add(o);
    }

    @Override
    public void notifyObserver() {
        for(IObeserver o: observers){
            o.updateGrid(grid);
        }
    }
}
