package main.model;

import main.model.initialPattern.Boat;
import main.model.initialPattern.InitialPattern;
import main.model.initialPattern.Ship;
import main.model.initialPattern.Square;
import main.view.IObeserver;

import java.util.ArrayList;

public class Model implements ISubject{

    /** take care of data store and computation - model*/

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

    /** set initial pattern*/
    public void setInitialPattern() {
//        InitialPattern[] patterns = {new Boat(), new Ship(), new Square()};
        InitialPattern[] patterns = {new Square()};
        for(InitialPattern pattern: patterns){
            for(Cell cell: pattern.getRedPattern()){
                grid.getCell(cell.getX(), cell.getY()).setCellStatus(CellStatus.RED);
            }
            for(Cell cell: pattern.getBluePattern()){
                grid.getCell(cell.getX(), cell.getY()).setCellStatus(CellStatus.BLUE);
            }
        }
    }

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
