package main.game;

import main.board.Cell;
import main.board.Grid;

import java.util.ArrayList;

public interface IObserver {
    public void updateGrid(Grid grid, ArrayList<Cell> cellToUpdate);
}
