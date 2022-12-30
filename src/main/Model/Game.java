package main.Model;

import main.View.IObeserver;

import java.util.ArrayList;

public class Game implements ISubject{

    /** take care of data store and computation*/

    ArrayList<IObeserver> obeservers = new ArrayList<>();


    private Grid grid = new Grid(40, 40);
    private Player player_red, player_blue;


    private Generation generation;

    public Game(){
        grid.getCell(41 % 40, 41 / 40).setCellStatus(CellStatus.RED);
        grid.getCell(42 % 40, 42 / 40).setCellStatus(CellStatus.RED);
        grid.getCell(43 % 40, 43 / 40).setCellStatus(CellStatus.RED);

        grid.getCell(81 % 40, 81 / 40).setCellStatus(CellStatus.BLUE);
        grid.getCell(82 % 40, 82 / 40).setCellStatus(CellStatus.BLUE);
        grid.getCell(83 % 40, 83 / 40).setCellStatus(CellStatus.BLUE);

        generation = new Generation(grid);
        player_red = new Player("", "R");
        player_blue = new Player("", "B");
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

//    public Generation getGeneration(){
//        return generation;
//    }

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

    public void updateCell(int buttonId, CellStatus status){
        getGrid().getCell(buttonId % 40, buttonId / 40).setCellStatus(status);
        notifyObserver();
    }

    @Override
    public void registerObserver(IObeserver o) {
        obeservers.add(o);
    }

    @Override
    public void notifyObserver() {
        for(IObeserver o: obeservers){
            o.updateButtons(grid);
        }
    }
}
