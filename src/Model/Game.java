package Model;

import View.HelloMessage;

public class Game {

    /** take care of data store and computation*/

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

    public Generation getGeneration(){
        return generation;
    }

    public Player getRedPlayer(){
        return player_red;
    }

    public Player getBluePlayer(){
        return player_blue;
    }

    public void evolve(){
        generation.aGeneration();
    }

}
