package main.Controller;

import main.Model.CellCollection;
import main.Model.CellStatus;
import main.Model.Game;
import main.Model.Grid;
import main.View.GUI;
import main.View.HelloMessage;

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

    public Controller(Game game, GUI gui){
        this.game = game;
        this.gui = gui;

//        game.setUpGame();

        for(JButton button: gui.getButtons()){
            button.addActionListener(new buttonListener());
        }

        gui.displayButtons(new Grid(40, 40));
        HelloMessage helloMessage = new HelloMessage();
        gui.displayButtons(game.getGrid());
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
                            buttons[i].setForeground(new Color(255,0,0));
                            buttons[i].setText("R");
                            action_life = true;
                            game.updateCell(i, CellStatus.RED);
//                            notifyObserver();
//                            gui.displayButtons(game.getGrid());
                            gui.unableOtherButton(""); /** player unable to hit other dead cells*/
                        } else if (buttons[i].getText().equals("B")) {
                            killAnEnemy(i);
//                            notifyObserver();
//                            gui.displayButtons(game.getGrid());
                            gui.unableOtherButton("B"); /** player unable to hit other blue cells*/
                        }
                        if(isPlayerTurnEnd("R")) {
                            gui.updateStats(game.getGrid());
                        }
                    }
                    else {
                        gui.setBlueTitle();
                        if (buttons[i].getText().equals("")) {
                            action_life = true;
                            buttons[i].setForeground(new Color(0,0,255));
                            buttons[i].setText("B");
                            game.updateCell(i, CellStatus.BLUE);
                            gui.unableOtherButton("");
                        } else if (buttons[i].getText().equals("R")) {
                            killAnEnemy(i);
                            gui.unableOtherButton("R"); /** player unable to hit other red cells*/
                        }
                        if(isPlayerTurnEnd("B")){
                            gui.updateStats(game.getGrid());
                        }
                    }
                }
            }
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
                gui.declareWinner(game.getBluePlayer());
            }
            else if(blue_cells.getCellNumber()==0){
                gui.declareWinner(game.getRedPlayer());
            }
        }


    }


}
