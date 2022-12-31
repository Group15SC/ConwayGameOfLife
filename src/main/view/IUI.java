package main.view;

import main.model.Grid;

import javax.swing.*;

public interface IUI {

    void displayGrid(Grid grid);
    void updateStats(Grid grid);
    void setRedTitle(String redName);
    void setBlueTitle(String blueName);
    JButton[] getButtons();
    JLabel getGeneration_info();
    JLabel getCell_info();
    void disableOtherButtons(String color);
    void setButtonFree();

}
