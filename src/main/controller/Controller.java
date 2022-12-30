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

public class Controller{


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
            button.addActionListener(new buttonListener());
        }

        gui.displayGrid(new Grid(40, 40));
        helloMessage = new HelloMessage();
        gui.displayGrid(game.getGrid());
        String redName = helloMessage.getRedPlayerName();
        game.setPlayerName(redName, "R");
        String blueName = helloMessage.getBluePlayerName();
        game.setPlayerName(blueName, "B");
        decideFirstTurn(redName, blueName);
    }

    /** check which who owns the first turn*/
    private void decideFirstTurn(String redName, String blueName){

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // red first
        if(blueName.compareTo(redName)>0){
            red_turn = true;
            gui.setRedTitle();
        }
        else {
            red_turn = false;
            gui.setBlueTitle();
        }

    }




    class buttonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton[] buttons = gui.getButtons();

            for(int i = 0; i < buttons.length; i++) {

                if(e.getSource() == buttons[i]) {
                    if (red_turn) {
                        gui.setRedTitle();
                        if (buttons[i].getText().equals("")) {
                            bringToLife(buttons[i], i, "R");
                        } else if (buttons[i].getText().equals("B")) {
                            killAnEnemy(i);
                            gui.unableOtherButton("B"); /** player unable to hit other blue cells*/
                        } else {
                            warnKillSelfCell();
                        }
                        if(isPlayerTurnEnd("R")) {
                            System.out.println("here");
                            gui.updateStats(game.getGrid());
                        }
                    }
                    else {
                        gui.setBlueTitle();
                        if (buttons[i].getText().equals("")) {
                            bringToLife(buttons[i], i, "B");
                        } else if (buttons[i].getText().equals("R")) {
                            killAnEnemy(i);
                            gui.unableOtherButton("R"); /** player unable to hit other red cells*/
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

        private void bringToLife(JButton button, int buttonId, String playerColor) {
            action_life = true;
            if(playerColor.equals("R")){
                button.setForeground(Color.RED);
                game.updateCell(buttonId, CellStatus.RED);
            } else {
                button.setForeground(Color.BLUE);
                game.updateCell(buttonId, CellStatus.BLUE);
            }
            button.setText(playerColor);
            gui.unableOtherButton("");
        }


        private void killAnEnemy(int buttonId) {
            action_kill = true;
            game.updateCell(buttonId, CellStatus.BLANK);
        }


        private boolean isPlayerTurnEnd(String color){
            if(action_life && action_kill) {
                System.out.println(color+"'s Turn Ends");
                game.evolve();
                action_life = false;
                action_kill = false;
                if(color.equals("R")){
                    red_turn = false;
                    gui.setBlueTitle();
                }
                if(color.equals("B")){
                    red_turn = true;
                    gui.setRedTitle();
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
                helloMessage.declareWinner(game.getBluePlayer());
            }
            else if(blue_cells.getCellNumber()==0){
                helloMessage.declareWinner(game.getRedPlayer());
            }
        }

        private void warnKillSelfCell(){
            JOptionPane.showMessageDialog(null, "Nah, you are killing your own cell");
        }

    }


}
