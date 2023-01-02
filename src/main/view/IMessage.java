package main.view;

import main.model.Player;

public interface IMessage {
    void setUpMessage();
    void handleColor();
    String handleName(int playerId);

    void setRedPlayerName(String redName);

    void setBluePlayerName(String blueName);

    String getInput(String text);

    String getRedPlayerName();

    String getBluePlayerName();

    String handleSameName(String name);

    void displayWinnerMessage(String winnerName);

    void warnKillSelfCell();
}
