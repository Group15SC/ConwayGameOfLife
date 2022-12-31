package main.controller.mockClasses;

import main.controller.Controller;
import main.model.Cell;
import main.model.CellCollection;
import main.model.CellStatus;
import main.model.Game;

public class MockAGame {

    Game game;
    MockUI ui;
    MockMessage message;
    Controller mockController;

    public MockAGame(){
        game = new Game();
        ui = new MockUI();
        message = new MockMessage();
        message.setRedPlayerName("ARedPlayer");
        message.setBluePlayerName("BluePlayer");

        mockController = new Controller(game, ui);
        mockController.setUpController(message);
    }

    /** set (0,0) to red, and kill other cells*/
    public void killOtherBlueCells(){
        CellCollection reds = new CellCollection(game.getGrid(), CellStatus.BLUE);
        for(Cell cell: reds){
            cell.setCellStatus(CellStatus.BLANK);
        }
        game.updateCell(0, CellStatus.BLUE);
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
