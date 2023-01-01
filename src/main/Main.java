package main;

import main.controller.Controller;
import main.model.Model;
import main.view.GUI;
import main.view.InteractionMessage;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        GUI gui = new GUI(model);
        InteractionMessage message = new InteractionMessage();
        Controller controller = new Controller(model, gui);
        controller.setUpController(message);
//        CellCollection reds = new CellCollection(game.getGrid(), CellStatus.RED);
//        CellCollection blues = new CellCollection(game.getGrid(), CellStatus.BLUE);
//        for(Cell red: reds){
//            System.out.println(red.getX()+","+red.getY());
//        }

    }
}