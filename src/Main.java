import Controller.Controller;
import Model.CellCollection;
import Model.Game;
import View.GUI;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GUI gui = new GUI(game);
        Controller controller = new Controller(game, gui);
    }
}