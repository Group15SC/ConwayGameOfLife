import main.controller.Controller;
import main.model.Game;
import main.view.GUI;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI(game);
        Controller controller = new Controller(game, gui);
    }
}