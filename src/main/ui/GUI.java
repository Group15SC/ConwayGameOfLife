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
    private JFrame whole = new JFrame();
    private JPanel title_panel = new JPanel();
    private JLabel title = new JLabel();
    private JLabel generation_info = new JLabel();
    private JLabel cell_info = new JLabel();
    private JPanel down_panel = new JPanel();
    private final JButton[] buttons = new JButton[1600];
    private Player player_red;
    private Player player_blue;
    private int turn;
    private Grid grid;
    private boolean notWin;
    private boolean red_turn;
    Generation generation;
    boolean action_kill;
    boolean action_life;
    public GUI() {

        whole.setTitle("Conway's Game of Life");
        whole.setSize(1200,1300);
        whole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        whole.setLayout(new BorderLayout());
        whole.setLocationRelativeTo(null);
        whole.setVisible(true);

        title.setBackground(new Color(25, 25,25));
        title.setForeground(new Color(255,140,0)); //set text color
        title.setFont(new Font("Ink Free", Font.BOLD, 80));
        title.setHorizontalAlignment(JLabel.CENTER);//set center alignment
        title.setVerticalAlignment(JLabel.CENTER);
        title.setText("Conway's Game of Life");
        title.setOpaque(true);

        generation_info.setBackground(new Color(25, 25,25));
        generation_info.setForeground(new Color(255,140,0)); //set text color
        generation_info.setFont(new Font("Ink Free", Font.BOLD, 30));
//        generation_info.setHorizontalAlignment(JLabel.CENTER); //set center alignment
//        generation_info.setVerticalAlignment(JLabel.WEST);
        generation_info.setText("generation");
        generation_info.setOpaque(true);

        cell_info.setBackground(new Color(25, 25,25));
        cell_info.setForeground(new Color(255,140,0)); //set text color
        cell_info.setFont(new Font("Ink Free", Font.BOLD, 30));
//        generation_info.setHorizontalAlignment(JLabel.CENTER); //set center alignment
//        generation_info.setVerticalAlignment(JLabel.WEST);
        cell_info.setText("cell");
        cell_info.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,1200,100);
        title_panel.setBackground(new Color(0,0,0));

        title_panel.add(title, BorderLayout.WEST);
        title_panel.add(generation_info, SpringLayout.EAST);
        title_panel.add(cell_info, SpringLayout.EAST);

        whole.add(title_panel, BorderLayout.NORTH);

        down_panel.setLayout(new GridLayout(40,40));

        whole.add(down_panel, BorderLayout.CENTER);

        for(int i = 0; i < buttons.length; i ++) {
            buttons[i] = new JButton("");
            buttons[i].setBackground(Color.WHITE);
//            buttons[i].setOpaque(true);
            down_panel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

    }

    public void setUp() {
        HelloMessage helloMessage = new HelloMessage();
        grid = new Grid(40, 40);
        notWin = true;
        action_life = false;
        action_kill = false;
        grid.getCell(1,0).setCellStatus(CellStatus.RED);
        grid.getCell(2,0).setCellStatus(CellStatus.RED);
        grid.getCell(3,0).setCellStatus(CellStatus.RED);

        grid.getCell(1,1).setCellStatus(CellStatus.BLUE);
        grid.getCell(2,1).setCellStatus(CellStatus.BLUE);
        grid.getCell(3,1).setCellStatus(CellStatus.BLUE);
        updateButtons(grid);
        player_red = new Player(helloMessage.getRedPlayerName(), "R");
        player_blue = new Player(helloMessage.getBluePlayerName(), "B");

        firstTurn();

    }
    public void gameOn() {

        turn = 0; // turn == 0 -> red;  turn == 1 -> blue
        while(notWin) {
            /**
            while(turn == 0) {
                labelRed.setVisible(true);
                generation = new Generation(grid);
                int before = generation.getNumberOfGen();
                generation.aGeneration();
                updateButtons(grid);
                if(generation.getNumberOfGen() != before) {
                    turn ++;
                }
            }
            while(turn == 1) {
                labelBlue.setVisible(true);
                generation = new Generation(grid);
                int before = generation.getNumberOfGen();
                generation.aGeneration();
                updateButtons(grid);
                if(generation.getNumberOfGen() != before) {
                    turn --;
                }
            }
             */

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < buttons.length; i++) {
            if(e.getSource() == buttons[i]) {
                if (red_turn) {
                    if (buttons[i].getText() == "") {
                        action_life = true;
//                        buttons[i].setBorderPainted(false);
//                        buttons[i].setOpaque(true);
//                        buttons[i].setBackground(new Color(255,0,0));
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("R");
                        buttons[i].setFont(new Font("Ink Free", Font.BOLD, 30));
                        title.setText("RED Player's Turn");
                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.RED);
                        if(action_life && action_kill) {
                            generation = new Generation(grid);
                            generation.aGeneration();
                            updateButtons(grid);
                            title.setText("BLUE Player's Turn");
                            red_turn = false;
                            action_life = false;
                            action_kill = false;
                            checkWinner();
                        }
                    } else if (buttons[i].getText() == "B") {
                        action_kill = true;
//                        buttons[i].setBorderPainted(false);
//                        buttons[i].setOpaque(true);
//                        buttons[i].setBackground(Color.WHITE);
                        buttons[i].setForeground(new Color(255,255,255));
                        buttons[i].setText("");
                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.BLANK);
                        title.setText("RED Player's Turn");
                        if(action_life && action_kill) {
                            generation = new Generation(grid);
                            generation.aGeneration();
                            updateButtons(grid);
                            title.setText("BLUE Player's Turn");
                            red_turn = false;
                            action_life = false;
                            action_kill = false;
                            checkWinner();
                        }
                    }
                }
                if (!red_turn) {
                    if (buttons[i].getText() == "") {
                        action_life = true;
//                        buttons[i].setBorderPainted(false);
//                        buttons[i].setOpaque(true);
//                        buttons[i].setBackground(Color.BLUE);
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("B");
                        buttons[i].setFont(new Font("Ink Free", Font.BOLD, 30));
                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.BLUE);
                        title.setText("BLUE Player's Turn");
                        if(action_life && action_kill) {
                            generation = new Generation(grid);
                            generation.aGeneration();
                            updateButtons(grid);
                            title.setText("RED Player's Turn");
                            red_turn = true;
                            action_life = false;
                            action_kill = false;
                            checkWinner();
                        }
                    } else if (buttons[i].getText() == "R") {
                        action_kill = true;
//                        buttons[i].setBorderPainted(false);
//                        buttons[i].setOpaque(true);
//                        buttons[i].setBackground(Color.WHITE);
                        buttons[i].setForeground(new Color(255,255,255));
                        buttons[i].setText("");
                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.BLANK);
                        title.setText("BLUE Player's Turn");
                        if(action_life && action_kill) {
                            generation = new Generation(grid);
                            generation.aGeneration();
                            updateButtons(grid);
                            title.setText("RED Player's Turn");
                            red_turn = true;
                            action_life = false;
                            action_kill = false;
                            checkWinner();
                        }
                    }
                }
            }
        }
    }

    private void updateButtons(Grid grid) {
        for(int i = 0; i < grid.getWidth(); i ++) {
            for(int j = 0; j < grid.getHeight(); j++) {
                if(grid.getCell(i, j).getCellStatus() == CellStatus.RED) {
//                    buttons[40 * j + i].setBorderPainted(false);
//                    buttons[40 * j + i].setOpaque(true);
//                    buttons[40 * j + i].setBackground(Color.RED);
                    buttons[40 * j + i].setForeground(new Color(255,0,0));
                    buttons[40 * j + i].setText("R");
                    buttons[40 * j + i].setFont(new Font("Ink Free", Font.BOLD, 30));
                }
                else if(grid.getCell(i, j).getCellStatus() == CellStatus.BLUE) {
//                    buttons[40 * j + i].setBorderPainted(false);
//                    buttons[40 * j + i].setOpaque(true);
//                    buttons[40 * j + i].setBackground(Color.BLUE);
                    buttons[40 * j + i].setForeground(new Color(0,0,255));
                    buttons[40 * j + i].setText("B");
                    buttons[40 * j + i].setFont(new Font("Ink Free", Font.BOLD, 30));
                }
                else {
//                    buttons[40 * j + i].setBorderPainted(false);
//                    buttons[40 * j + i].setOpaque(true);
//                    buttons[40 * j + i].setBackground(Color.BLUE);
                    buttons[40 * j + i].setForeground(new Color(255,255,255));
                    buttons[40 * j + i].setText("");
                    buttons[40 * j + i].setFont(new Font("Ink Free", Font.BOLD, 30));
                }
            }
        }
    }

    /** check if any player has win the game
     * should be inserted after each turn */
    private boolean checkWinner(){
        CellCollection red_cells = new CellCollection(grid, CellStatus.RED);
        CellCollection blue_cells = new CellCollection(grid, CellStatus.BLUE);
        if(red_cells.getCellNumber() == 0){
            declareWinner(player_blue);
            return true;
        }
        if(blue_cells.getCellNumber()==0){
            declareWinner(player_red);
            return true;
        }
        return false;
    }

    /** check which who owns the first turn*/
    private void firstTurn(){
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        // red first
        if(player_blue.getName().compareTo(player_red.getName())>0){
            red_turn = true;
            title.setText("RED Player's Turn");
//            title_panel.add(labelRed);
        }
        else {
            red_turn = false;
            title.setText("BLUE Player's Turn");
//            title_panel.add(labelBlue);
        }
    }

    /** make all buttons unable to choose, declare the winner on top panel */
    private void declareWinner(Player player){
        for(int i=0; i<buttons.length; i++){
            buttons[i].setEnabled(false);
        }
        title.setFont(new Font("Ink Free", Font.BOLD, 40));
        title.setText("Player " + player.getName() + " wins the game!");
    }

    /** use button id to set the corresponding cell status on grid*/
    private void updateCell(int buttonId, CellStatus status) {
        grid.getCell(buttonId % 40, buttonId / 40).setCellStatus(status);
    }

}
