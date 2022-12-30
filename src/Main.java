import main.Controller.Controller;
import main.Model.Game;
import main.View.GUI;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI(game);
        Controller controller = new Controller(game, gui);
    }
}