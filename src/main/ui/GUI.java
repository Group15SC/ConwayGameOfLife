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
    private JLabel game_info = new JLabel();
    private JPanel right_panel = new JPanel();
    private JPanel down_panel = new JPanel();
    private final TextField tf1 = new TextField();
    private final TextField tf2 = new TextField();
    private final JLabel labelRed = new JLabel("Red Player's turn");
    private final JLabel labelBlue = new JLabel("Blue Player's turn");
    private final JLabel label1 = new JLabel("Red Player's Name:");
    private final JLabel label2 = new JLabel("Blue Player's Name:");
    private final JButton start = new JButton("Start Game");
    private final JButton[] buttons = new JButton[1600];
    private String name_red;
    private String name_blue;
    private Player player_red;
    private Player player_blue;
    private int turn;
    private Grid grid;
    private boolean notWin;
    private Generation generation;
    public GUI() {

        whole.setTitle("Conway's Game of Life");
        whole.setSize(1200,1100);
        whole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        whole.setLayout(new BorderLayout());
        whole.setVisible(true);

        title.setBackground(new Color(25, 25,25));
        title.setForeground(new Color(255,140,0)); //set text color
        title.setFont(new Font("Ink Free", Font.BOLD, 60));
        title.setHorizontalAlignment(JLabel.CENTER); //set center alignment
        title.setText("Conway's Game of Life");
        title.setOpaque(true);

        game_info.setBackground(new Color(25, 25,25));
        game_info.setForeground(new Color(255,140,0)); //set text color
        game_info.setFont(new Font("Ink Free", Font.BOLD, 20));
        game_info.setHorizontalAlignment(JLabel.CENTER); //set center alignment
        game_info.setText("Game Info");
        game_info.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,1200,100);
        title_panel.setBackground(new Color(0,0,0));

        title_panel.add(title, BorderLayout.WEST);
        title_panel.add(game_info, BorderLayout.EAST);

        whole.add(title_panel, BorderLayout.NORTH);

        down_panel.setLayout(new GridLayout(40,40));

        whole.add(down_panel, BorderLayout.CENTER);

        for(int i = 0; i < buttons.length; i ++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setOpaque(true);
            down_panel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

    }

    public void setUp() {
        HelloMessage helloMessage = new HelloMessage();
        grid = new Grid(40, 40);
        notWin = true;
        grid.getCell(1,0).setCellStatus(CellStatus.RED);
        grid.getCell(2,0).setCellStatus(CellStatus.RED);
        grid.getCell(3,0).setCellStatus(CellStatus.RED);

        grid.getCell(1,1).setCellStatus(CellStatus.BLUE);
        grid.getCell(2,1).setCellStatus(CellStatus.BLUE);
        grid.getCell(3,1).setCellStatus(CellStatus.BLUE);
        updateButtons(grid);
        generation = new Generation(grid);
        player_red = new Player(helloMessage.getRedPlayerName(), "R");
        player_blue = new Player(helloMessage.getBluePlayerName(), "B");

        firstTurn();

    }
    public void gameOn() {

        turn = 0; // turn == 0 -> red;  turn == 1 -> blue
        while(notWin) {

            /*while(turn == 0) {
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
            }*/

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            if(e.getSource() == tf1) {
                name_red = new String(e.getActionCommand());
            }
            if(e.getSource() == tf2) {
                name_blue = new String(e.getActionCommand());
            }
            start.setEnabled(false);
        }
        for(int i = 0; i < buttons.length; i++) {
            if(e.getSource() == buttons[i]) {
                if (turn == 0) {
                    if (buttons[i].getBackground() == Color.WHITE) {
                        buttons[i].setBorderPainted(false);
                        buttons[i].setOpaque(true);
                        buttons[i].setBackground(new Color(255,0,0));
                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.RED);
                    } else if (buttons[i].getBackground() == Color.BLUE) {
                        buttons[i].setBorderPainted(false);
                        buttons[i].setOpaque(true);
                        buttons[i].setBackground(Color.WHITE);
                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.BLANK);
                    }
                }
                if (turn == 1) {
                    if (buttons[i].getBackground() == Color.WHITE) {
                        buttons[i].setBorderPainted(false);
                        buttons[i].setOpaque(true);
                        buttons[i].setBackground(Color.BLUE);
                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.BLUE);
                    } else if (buttons[i].getBackground() == Color.RED) {
                        buttons[i].setBorderPainted(false);
                        buttons[i].setOpaque(true);
                        buttons[i].setBackground(Color.WHITE);
                        grid.getCell(i % 40,i / 40).setCellStatus(CellStatus.BLANK);
                    }
                }
            }
        }
    }

    private void updateButtons(Grid grid) {
        for(int i = 0; i < grid.getWidth(); i ++) {
            for(int j = 0; j < grid.getHeight(); j++) {
                if(grid.getCell(i, j).getCellStatus() == CellStatus.RED) {
                    buttons[40 * j + i].setBorderPainted(false);
                    buttons[40 * j + i].setOpaque(true);
                    buttons[40 * j + i].setBackground(Color.RED);
                }
                else if(grid.getCell(i, j).getCellStatus() == CellStatus.BLUE) {
                    buttons[40 * j + i].setBorderPainted(false);
                    buttons[40 * j + i].setOpaque(true);
                    buttons[40 * j + i].setBackground(Color.BLUE);
                }
            }
        }
    }

    /** check if any player has win the game
     * should be inserted after each turn */
    private void checkWinner(){
        CellCollection red_cells = new CellCollection(grid, CellStatus.RED);
        CellCollection blue_cells = new CellCollection(grid, CellStatus.BLUE);
        if(red_cells.getCellNumber() == 0){
            declareWinner(player_blue);
        }
        if(blue_cells.getCellNumber()==0){
            declareWinner(player_red);
        }
    }

    private boolean red_turn;

    /** check which who owns the first turn*/
    private void firstTurn(){
        // red first
        if(player_blue.getName().compareTo(player_red.getName())>0){
            red_turn = true;
            title_panel.add(labelRed);
        }
        else {
            red_turn = false;
            title_panel.add(labelBlue);
        }
    }

    /** make all buttons unable to choose, declare the winner on top panel */
    private void declareWinner(Player player){
        for(int i=0; i<buttons.length; i++){
            buttons[i].setEnabled(false);
        }
        title.setText("Player " + player.getName() + " wins the game!");
    }

    /** use button id to set the corresponding cell status on grid*/
    private void updateCell(int buttonId, CellStatus status) {
        grid.getCell(buttonId % 40, buttonId / 40).setCellStatus(status);
    }

}
