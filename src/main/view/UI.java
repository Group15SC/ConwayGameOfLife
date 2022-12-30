package main.view;

import main.model.Grid;
import main.model.Player;

public interface UI {
    void displayGrid(Grid grid);
    void declareWinner(Player player);

}
