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

    }
}