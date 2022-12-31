package main.view;

import main.model.Player;

public interface IMessage {

    void setUpMessage();
    void handleColor();
    String handleName(int playerId);
    String getInput(String text);
    String getRedPlayerName();
    String getBluePlayerName();
    String handleSameName(String name);
    void displayWinnerMessage(String winnerName);
}
