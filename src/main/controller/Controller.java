package main.controller;

import main.exception.KillOwnCellWarning;
import main.model.*;
import main.view.IMessage;
import main.view.IUI;
import main.exception.KillOwnCellWarning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{


    /* abstraction of the functionality necessary to change the data stored in model
     *  build data-view connection */

    private final Model model;
    private final IUI ui;
    private boolean red_turn;
    private boolean action_life, action_kill, turn_end;
    private IMessage helloMessage;
    private Player winner;

    /**
     * construct the controller for model and ui
     * @param model: game model
     * @param ui: ui
     */
    public Controller(Model model, IUI ui){
        this.model = model;
        this.ui = ui;
        ui.setUpUI();
        for(JButton button: ui.getButtons()){
            button.addActionListener(this);
        }
    }

    /**
     * set up the controller
     * @param helloMessage: the interaction message
     */
    public void setUpController(IMessage helloMessage){
        this.helloMessage = helloMessage;
        ui.displayGrid(new Grid(40,40));
        helloMessage.setUpMessage();

        ui.displayGrid(model.getGrid());

        String redName = helloMessage.getRedPlayerName();
        model.setPlayerName(redName, "R");
        String blueName = helloMessage.getBluePlayerName();
        model.setPlayerName(blueName, "B");

        decideFirstTurn(redName, blueName);
        ui.updateStats(model.getGrid());
    }

    /**
     * decide which who owns the first turn by name alphabetically
     * @param redName: red player's name
     * @param blueName: blue player's name
     */
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

    /**
     * set some time lag to change the title of game
     * @param lag: desired time lag
     */
    private void setTimeLag(int lag){
        try{
            Thread.sleep(lag);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * handle the user input(click), control the logic of each turn
     * in each player's turn:
     * if he/she clicked a colored button, check if it belongs to enemy. If yes, kill it. If not, give warning
     * if he/she clicked an empty button, bring the cell to life
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){
        JButton[] buttons = ui.getButtons();

        for(int i = 0; i < buttons.length; i++) {

            if(e.getSource() == buttons[i]) {
                if (red_turn) {
                    ui.setRedTitle(model.getRedPlayer().getName());
                    if (buttons[i].getText().equals("")) {
                        bringToLife(buttons[i], i, "R");
                    } else {
                            try {
                                killAnEnemy(buttons[i], i, "R");
                            } catch (KillOwnCellWarning ex) {
                                helloMessage.warnKillSelfCell();
                            }
                    }
                    if(isPlayerTurnEnd("R")) {
                        ui.updateStats(model.getGrid());
                    }
                }
                else {
                    ui.setBlueTitle(model.getBluePlayer().getName());
                    if (buttons[i].getText().equals("")) {
                        bringToLife(buttons[i], i, "B");
                    }  else {
                            try {
                                killAnEnemy(buttons[i], i, "B");
                            } catch (KillOwnCellWarning ex) {
                                helloMessage.warnKillSelfCell();
                            }
                    }
                    if(isPlayerTurnEnd("B")){
                        ui.updateStats(model.getGrid());
                    }
                }
            }
        }
    }

    /**
     * bring an enemy cell back to life
     * @param button: the hit button
     * @param buttonId: the index of the hit button
     * @param playerColor: player's color (to decide what color should the cell be assigned)
     */
    private void bringToLife(JButton button, int buttonId, String playerColor) {
        action_life = true;
        button.setText(playerColor);
        if(playerColor.equals("R")){
            button.setForeground(Color.RED);
            model.updateCell(buttonId, CellStatus.RED);
        } else {
            button.setForeground(Color.BLUE);
            model.updateCell(buttonId, CellStatus.BLUE);
        }
        ui.disableOtherButtons("");
    }

    /**
     * kill a chosen enemy cell
     * @param button: the hit button
     * @param buttonId: the index of the hit button
     * @param actionOwnerColor: who hit the button
     * @throws KillOwnCellWarning: if the player hit his/her own button, throw exception
     */
    public void killAnEnemy(JButton button, int buttonId, String actionOwnerColor) throws KillOwnCellWarning {
        if(!button.getText().equals(actionOwnerColor)){
            action_kill = true;
            model.updateCell(buttonId, CellStatus.BLANK);
        } else {
            throw new KillOwnCellWarning();
        }
    }

    /**
     * check if the player end his/her turn
     * @param color: the color of the player
     * @return boolean: true: the player has kill an enemy and bring a dead cell to life; false: not done all yet
     */
    private boolean isPlayerTurnEnd(String color){
        if(action_life && action_kill) {
            turn_end = true;
            if(!checkWinner()){
                model.evolve();
                if(!checkWinner()){
                    action_life = false;
                    action_kill = false;
                    if(color.equals("R")){
                        red_turn = false;
                        ui.setBlueTitle(model.getBluePlayer().getName());
                    }
                    if(color.equals("B")){
                        red_turn = true;
                        ui.setRedTitle(model.getRedPlayer().getName());
                    }
                    ui.setButtonFree();
                }
            }
        }
        return turn_end;
    }

    /**
     * check if any player has win the game
     * @return boolean: true: a player wins; false: game continue, no winner
     */
    private boolean checkWinner(){
        if(model.getRedCells().getCellNumber() == 0){
            winner = model.getBluePlayer();
            ui.setWinnerTitle(winner.getName());
            endGame();
            return true;
        }
        else if(model.getBlueCells().getCellNumber()==0){
            winner = model.getRedPlayer();
            ui.setWinnerTitle(winner.getName());
            endGame();
            return true;
        }
        return false;
    }

    /**
     * a player has won the game, update the statistics and make sure no button can be clicked anymore
     */
    private void endGame(){
        ui.updateStats(model.getGrid());
        helloMessage.displayWinnerMessage(winner.getName());
        ui.disableOtherButtons("");
        ui.disableOtherButtons("R");
        ui.disableOtherButtons("B");
        ui.exitGame();
    }

    /**
     * @return boolean:
     * true: red player's turn;
     * false: blue player's turn
     */
    public boolean getRedTurn(){
        return red_turn;
    }

    /**
     * @return boolean:
     * true: has brought an empty cell to life;
     * false: no empty cell has been brought to life
     */
    public boolean getActionLife(){
        return action_life;
    }

    /**
     * @return boolean:
     * true: has kill an enemy cell;
     * false: no enemy cell has been killed successfully
     */
    public boolean getActionKill(){
        return action_kill;
    }

    /**
     * @return boolean:
     * true: a player has ends his/her turn
     * false: a turn has not been finished
     */
    public boolean getTurnEnd() { return turn_end; }

    /**
     * @return Player: the winner
     */
    public Player getWinner(){
        return winner;
    }

}

