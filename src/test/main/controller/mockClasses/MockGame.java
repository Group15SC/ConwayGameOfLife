package main.controller.mockClasses;

import main.controller.Controller;
import main.model.Cell;
import main.model.CellStatus;
import main.model.Model;

public class MockGame {

    Model model;
    MockUI ui;
    MockMessage message;
    Controller mockController;

    public MockGame(){
        model = new Model();
        ui = new MockUI();
        ui.setUpUI();
        message = new MockMessage();
        message.setRedPlayerName("ARedPlayer");
        message.setBluePlayerName("BluePlayer");
        mockController = new Controller(model, ui);
        mockController.setUpController(message);
        resetPattern();
    }

    private void resetPattern() {
        // empty current colored cells
        for(Cell red: model.getRedCells()){
            model.getGrid().getCell(red.getX(), red.getY()).setCellStatus(CellStatus.BLANK);
        }
        for(Cell blue: model.getBlueCells()){
            model.getGrid().getCell(blue.getX(), blue.getY()).setCellStatus(CellStatus.BLANK);
        }

        // cell (0,1) - set to red, cell (39, 1) - set to blue
        model.getGrid().getCell(0,1).setCellStatus(CellStatus.RED);
        model.getGrid().getCell(39,1).setCellStatus(CellStatus.BLUE);
        ui.displayGrid(model.getGrid());
    }

    public Model getGame() {
        return model;
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

    public void addCell(int buttonId, CellStatus status){
        model.updateCell(buttonId, status);
    }

}
