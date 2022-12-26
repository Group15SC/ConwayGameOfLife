package main.game;

import main.board.Cell;
import main.board.CellStatus;
import main.board.Grid;
import main.player.Player;

public class Turn {

    Boolean somebodyWin = false;

    Player player;
//    Player enemy;

    private Grid grid;

    /** require some input to indicate which cell is chosen
     *  for now just assume it is indicated with coordinate*/

    private String getColor(CellStatus status){
        switch (status){
            case RED -> {
                return "R";
            }
            case BLUE -> {
                return "B";
            }
        }
        return null;
    }

    private CellStatus getStatus(String color){
        switch (color){
            case "R" -> {
                return CellStatus.RED;
            }
            case "B" -> {
                return CellStatus.BLUE;
            }
        }
        return null;
    }


    private void killAnEnemyCell(int enemyX, int enemyY){
        Cell enemyCell = grid.getCell(enemyX,enemyY);
        String color = getColor(enemyCell.getCellStatus());
        while(color != player.getColor()){
            System.out.println("Wrong input");
        }
        enemyCell.setCellStatus(CellStatus.BLANK);
    }

    private void bringToLife(int selfX, int selfY){
        Cell cell = grid.getCell(selfX, selfY);
        CellStatus selfColor = getStatus(player.getColor());
        while(cell.getCellStatus()!=CellStatus.BLANK){
            System.out.println("The selected cell is not empty!");
        }
        cell.setCellStatus(selfColor);
    }

//    public void handleTurn(int killX, int killY, int colorX, int colorY){
//        killAnEnemyCell(killX, killY);
//        bringToLife(colorX, colorY);
//        Generation generation = new Generation(grid);
//        generation.aGeneration();
////        Player winner = checkWinner();
//    }

//    public Player checkWinner(){
//        Player winner = null;
//        if(player.getNumberOfCells()==0){
//            winner = player;
//        }
//        somebodyWin = true;
//        return winner;
//    }

    /** at the end of each turn, a new generation takes place*/

}
