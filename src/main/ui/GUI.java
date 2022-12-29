package main.ui;

import main.board.CellCollection;
import main.board.CellStatus;
import main.board.Grid;
import main.game.Generation;
import main.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    /** gui var */
    private JFrame whole = new JFrame();
    private JPanel title_panel = new JPanel();
    private JLabel title = new JLabel();
    private JLabel generation_info = new JLabel();
    private JLabel cell_info = new JLabel();
    private JPanel down_panel = new JPanel();
    private final JButton[] buttons = new JButton[1600];

    /** component declaration */
    private Player player_red;
    private Player player_blue;
    private final Grid grid = new Grid(40, 40);
    private final Generation generation = new Generation(grid);


    /** temporary variables */
    private boolean red_turn;
    private boolean action_kill;
    private boolean action_life;


    public GUI() {

        whole.setTitle("Conway's Game of Life");
        whole.setSize(1200,1300);
        whole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        whole.setLayout(new BorderLayout());
        whole.setLocationRelativeTo(null);
        whole.setVisible(true);

        title.setBackground(new Color(0, 0,0));
        title.setForeground(new Color(255,140,0)); //set text color
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));
        title.setHorizontalAlignment(JLabel.CENTER);//set center alignment
        title.setVerticalAlignment(JLabel.CENTER);
        title.setText("Conway's Game of Life");
        title.setOpaque(true);

        generation_info.setBackground(new Color(0, 0,0));
        generation_info.setForeground(new Color(0,255,0)); //set text color
        generation_info.setFont(new Font("Sans Serif", Font.BOLD, 30));
//        generation_info.setHorizontalAlignment(JLabel.CENTER); //set center alignment
//        generation_info.setVerticalAlignment(JLabel.WEST);
        generation_info.setText("generation");
        generation_info.setOpaque(true);
        generation_info.setVisible(false);

        cell_info.setBackground(new Color(0, 0,0));
        cell_info.setForeground(new Color(0,255,0)); //set text color
        cell_info.setFont(new Font("Sans Serif", Font.BOLD, 30));
//        generation_info.setHorizontalAlignment(JLabel.CENTER); //set center alignment
//        generation_info.setVerticalAlignment(JLabel.WEST);
        cell_info.setText("cell");
        cell_info.setOpaque(true);
        cell_info.setVisible(false);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,1200,100);
        title_panel.setBackground(new Color(0,0,0));

        title_panel.add(title, BorderLayout.WEST);
        title_panel.add(cell_info, BorderLayout.CENTER);
        title_panel.add(generation_info, BorderLayout.EAST);

        whole.add(title_panel, BorderLayout.NORTH);

        down_panel.setLayout(new GridLayout(40,40));

        whole.add(down_panel, BorderLayout.CENTER);

        for(int i = 0; i < buttons.length; i ++) {
            buttons[i] = new JButton("");
            buttons[i].setBackground(Color.WHITE);
//            buttons[i].setOpaque(true);
            down_panel.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("Sans Serif", Font.BOLD, 25));
        }

    }

    public void setUp() {
        HelloMessage helloMessage = new HelloMessage();
//        grid = new Grid(40, 40);
        action_life = false;
        action_kill = false;
        grid.getCell(1,0).setCellStatus(CellStatus.RED);
        grid.getCell(2,0).setCellStatus(CellStatus.RED);
        grid.getCell(3,0).setCellStatus(CellStatus.RED);

        grid.getCell(1,1).setCellStatus(CellStatus.BLUE);
        grid.getCell(2,1).setCellStatus(CellStatus.BLUE);
        grid.getCell(3,1).setCellStatus(CellStatus.BLUE);
        displayButtons(grid);
        player_red = new Player(helloMessage.getRedPlayerName(), "R");
        player_blue = new Player(helloMessage.getBluePlayerName(), "B");

        decideFirstTurn();
    }

    /** check which who owns the first turn*/
    private void decideFirstTurn(){

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

            // red first
            if(player_blue.getName().compareTo(player_red.getName())>0){
                red_turn = true;
                title.setText("RED Player's Turn");
            }
            else {
                red_turn = false;
                title.setText("BLUE Player's Turn");
            }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < buttons.length; i++) {

            if(e.getSource() == buttons[i]) {
                if (red_turn) {
                    title.setText("RED Player's Turn");
                    if (buttons[i].getText().equals("")) {
                        action_life = true;
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("R");
                        updateCell(i, CellStatus.RED);
                        unableOtherButton(""); /** player unable to hit other dead cells*/
//                        buttons[i].setBorderPainted(false);
//                        buttons[i].setOpaque(true);
//                        buttons[i].setBackground(new Color(255,0,0));

//                        buttons[i].setFont(new Font("Sans Serif", Font.BOLD, 30));
//                        title.setText("RED Player's Turn");

//                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.RED);
                    } else if (buttons[i].getText().equals("B")) {
                        killAnEnemy(i);
                        unableOtherButton("B"); /** player unable to hit other blue cells*/
//                        buttons[i].setBorderPainted(false);
//                        buttons[i].setOpaque(true);
//                        buttons[i].setBackground(Color.WHITE);
//                        title.setText("RED Player's Turn");
//                        isRedPlayerTurnEnd();
                    }
                    if(isRedPlayerTurnEnd()) {
                        CellCollection blueCells = new CellCollection(grid,CellStatus.BLUE);
                        CellCollection redCells = new CellCollection(grid,CellStatus.RED);
                        generation_info.setVisible(true);
                        cell_info.setVisible(true);
                        generation_info.setText("Generation:" + Generation.getNumberOfGen() + "   ");
                        cell_info.setText("   B:" + blueCells.getCellNumber() + "  " + "R:" + redCells.getCellNumber());
                    };
                }
                if (!red_turn) {
                    title.setText("BLUE Player's Turn");
                    if (buttons[i].getText().equals("")) {
                        action_life = true;
//                        buttons[i].setBorderPainted(false);
//                        buttons[i].setOpaque(true);
//                        buttons[i].setBackground(Color.BLUE);
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("B");
//                        buttons[i].setFont(new Font("Sans Serif", Font.BOLD, 30));
                        updateCell(i, CellStatus.BLUE);
                        unableOtherButton("");
//                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.BLUE);
//                        title.setText("BLUE Player's Turn");
//                        bluePlayerTurnEnd();
                    } else if (buttons[i].getText().equals("R")) {
                        killAnEnemy(i);
                        unableOtherButton("R"); /** player unable to hit other red cells*/
//                        buttons[i].setBorderPainted(false);
//                        buttons[i].setOpaque(true);
//                        buttons[i].setBackground(Color.WHITE);
//                        title.setText("BLUE Player's Turn");
//                        bluePlayerTurnEnd();
                    }
                    if(isBluePlayerTurnEnd()){
                        CellCollection blueCells = new CellCollection(grid,CellStatus.BLUE);
                        CellCollection redCells = new CellCollection(grid,CellStatus.RED);
                        generation_info.setVisible(true);
                        cell_info.setVisible(true);
                        generation_info.setText("Generation:" + Generation.getNumberOfGen() + "   ");
                        cell_info.setText("   B:" + blueCells.getCellNumber() + "  " + "R:" + redCells.getCellNumber());
                    }
                }
            }
        }
    }

    private void killAnEnemy(int buttonId) {
        action_kill = true;
        buttons[buttonId].setForeground(new Color(255,255,255));
        buttons[buttonId].setText("");
//        grid.getCell(buttonId % 40,buttonId / 40).setCellStatus(CellStatus.BLANK);
        updateCell(buttonId, CellStatus.BLANK);
    }

    private boolean isBluePlayerTurnEnd() {
        if(action_life && action_kill) {
//            generation = new Generation(grid);
            generation.aGeneration();
            displayButtons(grid);
//                            title.setText("RED Player's Turn");
            red_turn = true;
            action_life = false;
            setButtonFree("");
            action_kill = false;
            setButtonFree("R");
            title.setText("RED Player's Turn");
            checkWinner();
            return true;
        }
        return false;
    }

    private boolean isRedPlayerTurnEnd() {
        if(action_life && action_kill) {
//            generation = new Generation(grid);
            generation.aGeneration();
            displayButtons(grid);
            red_turn = false;
            action_life = false;
            setButtonFree("");
            action_kill = false;
            setButtonFree("B");
            title.setText("BLUE Player's Turn");
            checkWinner();
            return true;
        }return false;
    }

    private void displayButtons(Grid grid) {
        for(int i = 0; i < grid.getWidth(); i ++) {
            for(int j = 0; j < grid.getHeight(); j++) {
                if(grid.getCell(i, j).getCellStatus() == CellStatus.RED) {
//                    buttons[40 * j + i].setBorderPainted(false);
//                    buttons[40 * j + i].setOpaque(true);
//                    buttons[40 * j + i].setBackground(Color.RED);
                    buttons[40 * j + i].setForeground(new Color(255,0,0));
                    buttons[40 * j + i].setText("R");
//                    buttons[40 * j + i].setFont(new Font("Sans Serif", Font.BOLD, 30));
                }
                else if(grid.getCell(i, j).getCellStatus() == CellStatus.BLUE) {
//                    buttons[40 * j + i].setBorderPainted(false);
//                    buttons[40 * j + i].setOpaque(true);
//                    buttons[40 * j + i].setBackground(Color.BLUE);
                    buttons[40 * j + i].setForeground(new Color(0,0,255));
                    buttons[40 * j + i].setText("B");
//                    buttons[40 * j + i].setFont(new Font("Sans Serif", Font.BOLD, 30));
                }
                else {
//                    buttons[40 * j + i].setBorderPainted(false);
//                    buttons[40 * j + i].setOpaque(true);
//                    buttons[40 * j + i].setBackground(Color.BLUE);
                    buttons[40 * j + i].setForeground(new Color(255,255,255));
                    buttons[40 * j + i].setText("");
//                    buttons[40 * j + i].setFont(new Font("Sans Serif", Font.BOLD, 30));
                }
            }
        }
    }

    /**
     * check if any player has win the game
     * should be inserted after each turn
     */
    private void checkWinner(){
        CellCollection red_cells = new CellCollection(grid, CellStatus.RED);
        CellCollection blue_cells = new CellCollection(grid, CellStatus.BLUE);
        if(red_cells.getCellNumber() == 0){
            declareWinner(player_blue);
        }
        else if(blue_cells.getCellNumber()==0){
            declareWinner(player_red);
        }
    }



    /** make all buttons unable to choose, declare the winner on top panel */
    private void declareWinner(Player player){
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        title.setFont(new Font("Sans Serif", Font.BOLD, 40));
        title.setText("Player " + player.getName() + " wins the game!");
    }

    /** use button id to set the corresponding cell status on grid*/
    private void updateCell(int buttonId, CellStatus status) {
        grid.getCell(buttonId % 40, buttonId / 40).setCellStatus(status);
    }

    /** make other button unable to choose (get rid of duplicate choices)*/
    private void unableOtherButton(String color){

        for(JButton button: buttons){
            if(button.getText().equals(color)){
                button.setEnabled(false);
            }
        }

    }

    /** make buttons free to choose again */
    private void setButtonFree(String color){

        for(JButton button: buttons){
            if(button.getText().equals(color)){
                button.setEnabled(true);
            }
        }

    }


}
