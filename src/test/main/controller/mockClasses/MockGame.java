package main.controller.mockClasses;

import main.controller.Controller;
import main.model.Cell;
import main.model.CellStatus;
import main.model.Game;
import main.model.Player;

public class MockGame {

    Game game;
    MockUI ui;
    MockMessage message;
    Controller mockController;

    public MockGame(){
        game = new Game();
        ui = new MockUI();
        message = new MockMessage();
        message.setRedPlayerName("ARedPlayer");
        message.setBluePlayerName("BluePlayer");
        mockController = new Controller(game, ui);
        mockController.setUpController(message);
        resetPattern();
    }

    private void resetPattern() {
        // empty current colored cells
        for(Cell red: game.getRedCells()){
            game.getGrid().getCell(red.getX(), red.getY()).setCellStatus(CellStatus.BLANK);
        }
        for(Cell blue: game.getBlueCells()){
            game.getGrid().getCell(blue.getX(), blue.getY()).setCellStatus(CellStatus.BLANK);
        }

        // cell (0,1) - set to red, cell (39, 1) - set to blue
        game.getGrid().getCell(0,1).setCellStatus(CellStatus.RED);
        game.getGrid().getCell(39,1).setCellStatus(CellStatus.BLUE);
        ui.displayGrid(game.getGrid());
    }

    public Game getGame() {
        return game;
    }

    public MockUI getUi() {
        return ui;
    }

    public MockMessage getMessage() {
        return message;
    }

    public Controller getMockController() {
        return mockController;
    }

}
