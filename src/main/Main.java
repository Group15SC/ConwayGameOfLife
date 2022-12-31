package main;

import main.controller.Controller;
import main.model.Game;
import main.view.GUI;
import main.view.HelloMessage;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI(game);
        HelloMessage message = new HelloMessage();
        Controller controller = new Controller(game, gui, message);
        controller.setUpController(gui);
    }
}