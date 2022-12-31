package main.view;

import main.model.*;

import javax.swing.*;
import java.awt.*;

public class GUI implements IObeserver, UI{

    /** take care of the view */

    /** gui var */
    private JFrame whole = new JFrame();
    private JPanel title_panel = new JPanel();
    private JPanel stats_panel = new JPanel();
    private JPanel info_board = new JPanel();
    private JLabel title = new JLabel();
    private JLabel generation_info = new JLabel();
    private JLabel cell_info = new JLabel();
    private JPanel down_panel = new JPanel();
    private final JButton[] buttons = new JButton[1600];

    /** component declaration */


    public GUI(Game game) {

        game.registerObserver(this);

        whole.setTitle("Conway's Game of Life");
        whole.setSize(1100,1300);
        whole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        whole.setLayout(new BorderLayout());
        whole.setLocationRelativeTo(null);
        whole.setBackground(new Color(0,0,0));
        whole.setVisible(true);

        title.setBackground(new Color(0, 0,0));
        title.setForeground(new Color(0,255,0)); //set text color
        title.setFont(new Font("Sans Serif", Font.BOLD, 80));
        title.setHorizontalAlignment(JLabel.CENTER);//set center alignment
        title.setVerticalAlignment(JLabel.CENTER);
        title.setText("Conway's Game of Life");
        title.setOpaque(true);


        generation_info.setBackground(new Color(0, 0,0));
        generation_info.setForeground(new Color(0,255,0)); //set text color
        generation_info.setFont(new Font("Sans Serif", Font.BOLD, 30));
        generation_info.setText("generation");
        generation_info.setOpaque(true);
        generation_info.setVisible(false);

        cell_info.setBackground(new Color(0, 0,0));
        cell_info.setForeground(new Color(0,255,0)); //set text color
        cell_info.setFont(new Font("Sans Serif", Font.BOLD, 30));
        cell_info.setText("cell");
        cell_info.setOpaque(true);
        cell_info.setVisible(false);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,1100,100);
        title_panel.setBackground(new Color(0,0,0));
        title_panel.add(title, BorderLayout.NORTH);

        stats_panel.setLayout(new BorderLayout());
        stats_panel.setBounds(0,0,1100,100);
        stats_panel.setBackground(new Color(0,0,0));
        stats_panel.add(cell_info, BorderLayout.WEST);
        stats_panel.add(generation_info, BorderLayout.EAST);

        info_board.setLayout(new BorderLayout());
        info_board.setBounds(0,0,1100,100);
        info_board.setBackground(new Color(0,0,0));
        info_board.add(title_panel, BorderLayout.NORTH);
        info_board.add(stats_panel, BorderLayout.SOUTH);


        whole.add(info_board, BorderLayout.NORTH);

        down_panel.setLayout(new GridLayout(40,40));
        down_panel.setBackground(new Color(0,0,0));

        whole.add(down_panel, BorderLayout.CENTER);

        for(int i = 0; i < buttons.length; i ++) {
            buttons[i] = new JButton("");
            buttons[i].setBackground(Color.WHITE);
            down_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Sans Serif", Font.BOLD, 20));
        }

    }

    /** base on the grid, update statistics */
    public void updateStats(Grid grid) {
        CellCollection blueCells = new CellCollection(grid, CellStatus.BLUE);
        CellCollection redCells = new CellCollection(grid,CellStatus.RED);
        generation_info.setVisible(true);
        cell_info.setVisible(true);
        generation_info.setText("Generation:  " + Generation.getNumberOfGen() + "   ");
        cell_info.setText("   Blue Cells: " + blueCells.getCellNumber() + "  " + "Red Cells: " + redCells.getCellNumber());
    }



    /** make other button unable to choose (get rid of duplicate choices)*/
    public void disableOtherButtons(String color){

        for(JButton button: buttons){
            if(button.getText().equals(color)){
                button.setEnabled(false);
            }
        }

    }

    /** make buttons free to choose again */
    public void setButtonFree(){

        for(JButton button: buttons){
            button.setEnabled(true);
        }

    }

    /** set title to Red player*/
    public void setRedTitle(String redName){
        title.setText(redName+"'s Turn");
        title.setForeground(new Color(255, 0,0));
    }

    public void setBlueTitle(String blueName){
        title.setText(blueName+"'s Turn");
        title.setForeground(new Color(0,0,255));
    }

    public JButton[] getButtons(){
        return buttons;
    }

    @Override
    public void updateGrid(Grid grid) {
        displayGrid(grid);
    }

    @Override
    public void displayGrid(Grid grid) {

        for(int i = 0; i < grid.getWidth(); i ++) {
            for(int j = 0; j < grid.getHeight(); j++) {

                JButton aButton = buttons[40 * j + i];
                switch (grid.getCell(i, j).getCellStatus()) {
                    case RED -> {
                        aButton.setForeground(new Color(255, 0, 0));
                        aButton.setText("R");
                    }
                    case BLUE -> {
                        aButton.setForeground(new Color(0, 0, 255));
                        aButton.setText("B");
                    }
                    default -> {
                        aButton.setOpaque(true);
                        aButton.setBackground(new Color(0,0,0));
                        aButton.setBorder(BorderFactory.createLineBorder(new Color(47, 46, 46),1));
                        aButton.setText("");
                    }
                }

            }
        }
    }

    public JLabel getGeneration_info() {
        return generation_info;
    }

    public JLabel getCell_info() {
        return cell_info;
    }

}
