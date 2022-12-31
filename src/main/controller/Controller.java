package main.controller;

import main.model.*;
import main.view.HelloMessage;
import main.view.IMessage;
import main.view.IUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{


    /** abstraction of the functionality necessary to change the data stored in model
     *  build data-view connection */

    private final Game game;
    private final IUI ui;
    private boolean red_turn;
    private boolean action_life, action_kill;
    private IMessage helloMessage;
    private Player winner;

    public Controller(Game game, IUI ui){
        this.game = game;
        this.ui = ui;
        for(JButton button: ui.getButtons()){
            button.addActionListener(this);
        }
    }

    public void setUpController(IMessage helloMessage){
        this.helloMessage = helloMessage;
        ui.displayGrid(new Grid(40,40));

        helloMessage.setUpMessage();

//        game.setInitialPattern();
        ui.displayGrid(game.getGrid());

        String redName = helloMessage.getRedPlayerName();
        game.setPlayerName(redName, "R");
        String blueName = helloMessage.getBluePlayerName();
        game.setPlayerName(blueName, "B");

        decideFirstTurn(redName, blueName);
        ui.updateStats(game.getGrid());
    }

    /** check which who owns the first turn*/
    private void decideFirstTurn(String redName, String blueName){

        setTimeLag(800);

        // red first
        if(blueName.compareTo(redName)>0){
            red_turn = true;
            ui.setRedTitle(redName);
        }
        else {
            red_turn = false;
            ui.setBlueTitle(blueName);
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
        JButton[] buttons = ui.getButtons();

        for(int i = 0; i < buttons.length; i++) {

            if(e.getSource() == buttons[i]) {
                if (red_turn) {
                    ui.setRedTitle(game.getRedPlayer().getName());
                    if (buttons[i].getText().equals("")) {
                        bringToLife(buttons[i], i, "R");
                    } else if (buttons[i].getText().equals("B")) {
                        killAnEnemy(i);
                        ui.disableOtherButtons("B"); /** player unable to hit other blue cells*/
                    } else {
                        warnKillSelfCell();
                    }
                    if(isPlayerTurnEnd("R")) {
                        ui.updateStats(game.getGrid());
                    }
                }
                else {
                    ui.setBlueTitle(game.getBluePlayer().getName());
                    if (buttons[i].getText().equals("")) {
                        bringToLife(buttons[i], i, "B");
                    } else if (buttons[i].getText().equals("R")) {
                        killAnEnemy(i);
                        ui.disableOtherButtons("R"); /** player unable to hit other red cells*/
                    } else {
                        warnKillSelfCell();
                    }
                    if(isPlayerTurnEnd("B")){
                        ui.updateStats(game.getGrid());
                    }
                }
            }
        }
    }


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
        ui.disableOtherButtons("");
    }

    private void killAnEnemy(int buttonId) {
        action_kill = true;
        game.updateCell(buttonId, CellStatus.BLANK);
    }


    private boolean isPlayerTurnEnd(String color){
        if(action_life && action_kill) {
            if(!checkWinner()){
                game.evolve();
                if(!checkWinner()){
                    action_life = false;
                    action_kill = false;
                    if(color.equals("R")){
                        red_turn = false;
                        ui.setBlueTitle(game.getBluePlayer().getName());
                    }
                    if(color.equals("B")){
                        red_turn = true;
                        ui.setRedTitle(game.getRedPlayer().getName());
                    }
                    ui.setButtonFree();
                }
            }
            return true;
        }
        return false;
    }


    /**
     * check if any player has win the game
     * should be inserted after each turn
     */
    private boolean checkWinner(){
        CellCollection red_cells = new CellCollection(game.getGrid(), CellStatus.RED);
        CellCollection blue_cells = new CellCollection(game.getGrid(), CellStatus.BLUE);
        if(red_cells.getCellNumber() == 0){
            winner = game.getBluePlayer();
            endGame();
            return true;
        }
        else if(blue_cells.getCellNumber()==0){
            winner = game.getRedPlayer();
            endGame();
            return true;
        }
        return false;
    }

    private void warnKillSelfCell(){
        JOptionPane.showMessageDialog(null, "Nah, you are killing your own cell",
                "Trying to kill your self!", JOptionPane.DEFAULT_OPTION);

    }

    private void endGame(){
        ui.updateStats(game.getGrid());
        helloMessage.displayWinnerMessage(winner.getName());
        ui.disableOtherButtons("");
        ui.disableOtherButtons("R");
        ui.disableOtherButtons("B");
    }

    protected boolean getRedTurn(){
        return red_turn;
    }

    public boolean getActionLife(){
        return action_life;
    }

    public boolean getActionKill(){
        return action_kill;
    }

}

