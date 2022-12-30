package main.View;

import main.Model.Grid;
import main.Model.Player;

public interface UI {
    void displayGrid(Grid grid);
    void declareWinner(Player player);

}
