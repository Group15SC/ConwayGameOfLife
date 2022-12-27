package main.ui;

import main.board.Cell;
import main.board.CellStatus;
import main.board.Grid;
import main.game.Generation;
import main.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
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
        JFrame whole = new JFrame();
        whole.setTitle("Conway's Game of Life");
        whole.setSize(1000,800);
        whole.setLocationRelativeTo(null);
        whole.setDefaultCloseOperation(EXIT_ON_CLOSE);
        whole.setLayout(new BorderLayout());

        Dimension dim_right = new Dimension(150,0);//set the size of right side
        Dimension dim_left = new Dimension(550,0);//set the size of left side
        Dimension dim_buttons = new Dimension(140,50);//set the size of right buttons
        this.setPreferredSize(dim_left);

        /* left side consists of 40x40 buttons */
        JPanel jp_left = new JPanel(new GridLayout(40,40));

//        FrameListener fl = new FrameListener(this);

        whole.add(jp_left, BorderLayout.CENTER);
        for(int i = 0; i < buttons.length; i ++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setOpaque(true);
//            buttons[i].addActionListener(fl);
            jp_left.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        /* right side consists of 2 JLabels, 4 textFields and 1 JButton */
        JPanel jp_right = new JPanel();
        jp_right.setPreferredSize(dim_right); // set the right part's size
        whole.add(jp_right,BorderLayout.EAST);
        jp_right.setLayout(new FlowLayout());
        labelRed.setPreferredSize(dim_buttons);
        labelRed.setForeground(Color.RED);
        labelBlue.setPreferredSize(dim_buttons);
        labelBlue.setForeground(Color.BLUE);
        labelRed.setVisible(false);
        labelBlue.setVisible(false);
        label1.setPreferredSize(dim_buttons); // set every part of right part's size
        tf1.setPreferredSize(dim_buttons); // set every part of right part's size
        label2.setPreferredSize(dim_buttons); // set every part of right part's size
        tf2.setPreferredSize(dim_buttons); // set every part of right part's size
        start.setPreferredSize(dim_buttons); // set every part of right part's size

        /* add components to right panel */
        jp_right.add(labelRed);
        jp_right.add(labelBlue);
        jp_right.add(label1);
        jp_right.add(tf1);
        jp_right.add(label2);
        jp_right.add(tf2);
        jp_right.add(start);

        /* add ActionListener to right part */
//        RightListener rightListener = new RightListener(this);
//        tf1.addActionListener(rightListener);
//        tf2.addActionListener(rightListener);
//        start.addActionListener(rightListener);
        tf1.addActionListener(this);
        tf2.addActionListener(this);
        start.addActionListener(this);

        /* make sure the whole JFrame is visible */
        whole.setVisible(true);
    }

    public void setUp() {

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
        player_red = new Player(name_red, "R");
        player_blue = new Player(name_blue, "B");
    }
    public void gameOn() {

        turn = 0; // turn == 0 -> red;  turn == 1 -> blue
        while(notWin) {

//            while(turn == 0) {
//                labelRed.setVisible(true);
//                generation = new Generation(grid);
//                int before = generation.getNumberOfGen();
//                generation.aGeneration();
//                updateButtons(grid);
//                if(generation.getNumberOfGen() != before) {
//                    turn ++;
//                }
//            }
//            while(turn == 1) {
//                labelBlue.setVisible(true);
//                generation = new Generation(grid);
//                int before = generation.getNumberOfGen();
//                generation.aGeneration();
//                updateButtons(grid);
//                if(generation.getNumberOfGen() != before) {
//                    turn --;
//                }
//            }

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
                        buttons[i].setBackground(Color.RED);
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


}