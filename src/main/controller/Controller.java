package main.controller;

import main.model.CellCollection;
import main.model.CellStatus;
import main.model.Game;
import main.model.Grid;
import main.view.GUI;
import main.view.HelloMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{


    /** abstraction of the functionality necessary to change the data stored in model
     *  build data-view connection */

    private final Game game;
    private final GUI gui;
    private boolean red_turn;
    private boolean action_life, action_kill;
    private HelloMessage helloMessage;

    public Controller(Game game, GUI gui){
        this.game = game;
        this.gui = gui;

//        game.setUpGame();

        for(JButton button: gui.getButtons()){
            button.addActionListener(this);
        }

        gui.displayGrid(new Grid(40, 40));
        helloMessage = new HelloMessage();
        gui.displayGrid(game.getGrid());
        String redName = helloMessage.getRedPlayerName();
        game.setPlayerName(redName, "R");
        String blueName = helloMessage.getBluePlayerName();
        game.setPlayerName(blueName, "B");
        decideFirstTurn(redName, blueName);
        gui.updateStats(game.getGrid());
    }

    /** check which who owns the first turn*/
    private void decideFirstTurn(String redName, String blueName){

        setTimeLag(800);

        // red first
        if(blueName.compareTo(redName)>0){
            red_turn = true;
            gui.setRedTitle(redName);
        }
        else {
            red_turn = false;
            gui.setBlueTitle(blueName);
        }

    }


    private void setTimeLag(int lag){
        try{
            Thread.sleep(lag);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton[] buttons = gui.getButtons();

        for(int i = 0; i < buttons.length; i++) {

            if(e.getSource() == buttons[i]) {
                if (red_turn) {
                    gui.setRedTitle(game.getRedPlayer().getName());
                    if (buttons[i].getText().equals("")) {
                        bringToLife(buttons[i], i, "R");
                    } else if (buttons[i].getText().equals("B")) {
                        killAnEnemy(i);
                        gui.disableOtherButtons("B"); /** player unable to hit other blue cells*/
                    } else {
                        warnKillSelfCell();
                    }
                    if(isPlayerTurnEnd("R")) {
                        gui.updateStats(game.getGrid());
                    }
                }
                else {
                    gui.setBlueTitle(game.getBluePlayer().getName());
                    if (buttons[i].getText().equals("")) {
                        bringToLife(buttons[i], i, "B");
                    } else if (buttons[i].getText().equals("R")) {
                        killAnEnemy(i);
                        gui.disableOtherButtons("R"); /** player unable to hit other red cells*/
                    } else {
                        warnKillSelfCell();
                    }
                    if(isPlayerTurnEnd("B")){
                        gui.updateStats(game.getGrid());
                    }
                }
            }
        }
    }


//    class buttonListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//
//        }
//    }

    private void bringToLife(JButton button, int buttonId, String playerColor) {
        action_life = true;
        button.setText(playerColor);
        if(playerColor.equals("R")){
            button.setForeground(Color.RED);
            game.updateCell(buttonId, CellStatus.RED);
        } else {
            button.setForeground(Color.BLUE);
            game.updateCell(buttonId, CellStatus.BLUE);
        }
        gui.disableOtherButtons("");
    }

    private void killAnEnemy(int buttonId) {
        action_kill = true;
        game.updateCell(buttonId, CellStatus.BLANK);
    }


    private boolean isPlayerTurnEnd(String color){
        if(action_life && action_kill) {
            checkWinner();
            game.evolve();
            action_life = false;
            action_kill = false;
            if(color.equals("R")){
                red_turn = false;
                gui.setBlueTitle(game.getBluePlayer().getName());
            }
            if(color.equals("B")){
                red_turn = true;
                gui.setRedTitle(game.getRedPlayer().getName());
            }
            gui.setButtonFree();
            checkWinner();
            return true;
        } return false;
    }


    /**
     * check if any player has win the game
     * should be inserted after each turn
     */
    private void checkWinner(){
        CellCollection red_cells = new CellCollection(game.getGrid(), CellStatus.RED);
        CellCollection blue_cells = new CellCollection(game.getGrid(), CellStatus.BLUE);
        if(red_cells.getCellNumber() == 0){
            helloMessage.displayWinnerMessage(game.getBluePlayer());
            gui.disableOtherButtons("");
            gui.disableOtherButtons("R");
            gui.disableOtherButtons("B");
        }
        else if(blue_cells.getCellNumber()==0){
            helloMessage.displayWinnerMessage(game.getRedPlayer());
            gui.disableOtherButtons("");
            gui.disableOtherButtons("R");
            gui.disableOtherButtons("B");
        }
    }

    private void warnKillSelfCell(){
        JOptionPane.showMessageDialog(null, "Nah, you are killing your own cell",
                                        "Trying to kill your self!", JOptionPane.DEFAULT_OPTION);

    }


}
