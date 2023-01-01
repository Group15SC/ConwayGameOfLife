package main.controller.mockClasses;

import main.view.IMessage;

public class MockMessage implements IMessage {

    private String player1_name;
    private String player2_name;
    private String player1_color;
    private String player2_color;

    public MockMessage(){
    }

    @Override
    public void setUpMessage() {
    }

    @Override
    public void handleColor() {
        player1_color = "R";
        player2_color = "B";
    }

    @Override
    public String handleName(int playerId) {
        if(playerId==1){
            return player1_name;
        }
        if(playerId==2){
            return player2_name;
        }
        return null;
    }

    @Override
    public void setRedPlayerName(String redName){
        this.player1_name = redName;
    }

    @Override
    public void setBluePlayerName(String blueName){
        this.player2_name = blueName;
    }

    @Override
    public String getInput(String text) {
        return null;
    }

    @Override
    public String getRedPlayerName() {
        return player1_name;
    }

    @Override
    public String getBluePlayerName() {
        return player2_name;
    }

    @Override
    public String handleSameName(String name) {
        return null;
    }

    @Override
    public void displayWinnerMessage(String winnerName) {

    }


}
