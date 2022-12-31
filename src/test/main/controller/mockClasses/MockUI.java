package main.controller.mockClasses;

import main.model.Grid;
import main.view.IUI;

import javax.swing.*;

public class MockUI implements IUI {

    private final JButton[] buttons = new JButton[1600];

    public MockUI(){
        for(int i=0; i<buttons.length; i++){
            buttons[i] = new JButton();
        }
    }


    @Override
    public void displayGrid(Grid grid) {
        for(int i=0; i<grid.getWidth();i++){
            for(int j=0; j<grid.getHeight();j++){
                JButton aButton = buttons[5*j+i];
                switch(grid.getCell(i, j).getCellStatus()){
                    case RED -> aButton.setText("R");
                    case BLUE -> aButton.setText("B");
                    default -> aButton.setText("");
                }
            }
        }
    }

    @Override
    public void updateStats(Grid grid) {

    }

    @Override
    public void setRedTitle(String redName) {

    }

    @Override
    public void setBlueTitle(String blueName) {

    }

    @Override
    public JButton[] getButtons() {
        return buttons;
    }

    @Override
    public JLabel getGeneration_info() {
        return null;
    }

    @Override
    public JLabel getCell_info() {
        return null;
    }

    @Override
    public void disableOtherButtons(String color) {

    }

    @Override
    public void setButtonFree() {

    }
}
