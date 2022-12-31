package main;

import main.controller.Controller;
import main.model.Cell;
import main.model.CellCollection;
import main.model.CellStatus;
import main.model.Game;
import main.view.GUI;
import main.view.HelloMessage;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI(game);
        HelloMessage message = new HelloMessage();
        Controller controller = new Controller(game, gui);
        controller.setUpController(message);
//        CellCollection reds = new CellCollection(game.getGrid(), CellStatus.RED);
//        CellCollection blues = new CellCollection(game.getGrid(), CellStatus.BLUE);
//        for(Cell red: reds){
//            System.out.println(red.getX()+","+red.getY());
//        }
    }
}