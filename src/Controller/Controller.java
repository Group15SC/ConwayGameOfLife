package Controller;

import Model.CellCollection;
import Model.CellStatus;
import Model.Game;
import Model.Grid;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    /** abstraction of the functionality necessary to change the data stored in model
     *  build data-view connection */

    private final Game game;
    private final GUI gui;
    private boolean red_turn;
    private boolean action_life, action_kill;

    public Controller(Game game, GUI gui){
        this.game = game;
        this.gui = gui;

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
                            updateCell(i, CellStatus.RED);
                            gui.displayButtons(game.getGrid());
                            gui.unableOtherButton(""); /** player unable to hit other dead cells*/
                        } else if (buttons[i].getText().equals("B")) {
                            killAnEnemy(i);
                            gui.displayButtons(game.getGrid());
                            gui.unableOtherButton("B"); /** player unable to hit other blue cells*/
                        }
                        if(isRedPlayerTurnEnd()) {
                            gui.updateStats(game.getGrid());
                        }
                    }
                    else {
                        gui.setBlueTitle();
                        if (buttons[i].getText().equals("")) {
                            action_life = true;
                            buttons[i].setForeground(new Color(0,0,255));
                            buttons[i].setText("B");
                            updateCell(i, CellStatus.BLUE);
                            gui.displayButtons(game.getGrid());
                            gui.unableOtherButton("");
                        } else if (buttons[i].getText().equals("R")) {
                            killAnEnemy(i);
                            gui.displayButtons(game.getGrid());
                            gui.unableOtherButton("R"); /** player unable to hit other red cells*/
                        }
                        if(isBluePlayerTurnEnd()){
                            gui.updateStats(game.getGrid());
                        }
                    }
                }
            }
        }

        private void killAnEnemy(int buttonId) {
            action_kill = true;
            updateCell(buttonId, CellStatus.BLANK);
        }

        private boolean isRedPlayerTurnEnd() {
            if(action_life && action_kill) {
                System.out.println("Red here");
                game.evolve();
                gui.displayButtons(game.getGrid());
                red_turn = false;
                action_life = false;
                action_kill = false;
                gui.setButtonFree();
                gui.setBlueTitle();
                checkWinner();
                return true;
            }return false;
        }

        private boolean isBluePlayerTurnEnd() {
            if(action_life && action_kill) {
                System.out.println("Blue here");
                game.evolve();
                gui.displayButtons(game.getGrid());
                red_turn = true;
                action_life = false;
                action_kill = false;
                gui.setButtonFree();
                gui.setRedTitle();
                checkWinner();
                return true;
            }
            return false;
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

        public void updateCell(int buttonId, CellStatus status) {
            game.getGrid().getCell(buttonId % 40, buttonId / 40).setCellStatus(status);
        }

    }


}
