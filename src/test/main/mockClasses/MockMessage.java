package main.mockClasses;

import main.view.IMessage;

public class MockMessage implements IMessage {
    @Override
    public void setUpMessage() {

    }

    @Override
    public void handleColor() {

    }

    @Override
    public String handleName(int playerId) {
        return null;
    }

    @Override
    public String getInput(String text) {
        return null;
    }

    @Override
    public String getRedPlayerName() {
        return null;
    }

    @Override
    public String getBluePlayerName() {
        return null;
    }

    @Override
    public String handleSameName(String name) {
        return null;
    }

    @Override
    public void displayWinnerMessage(String winnerName) {

    }
}
