package View;


import Model.*;

import javax.swing.*;
import java.awt.*;

public class GUI{

    /** take care of the view */


    /** gui var */
    private JFrame whole = new JFrame();
    private JPanel title_panel = new JPanel();
    private JLabel title = new JLabel();
    private JLabel generation_info = new JLabel();
    private JLabel cell_info = new JLabel();
    private JPanel down_panel = new JPanel();
    private final JButton[] buttons = new JButton[1600];

    /** component declaration */
//    private final Grid grid = new Grid(40, 40);


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
            down_panel.add(buttons[i]);
//            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font("Sans Serif", Font.BOLD, 25));
        }

    }

    public void updateStats(Grid grid) {
        CellCollection blueCells = new CellCollection(grid,CellStatus.BLUE);
        CellCollection redCells = new CellCollection(grid,CellStatus.RED);
        generation_info.setVisible(true);
        cell_info.setVisible(true);
        generation_info.setText("Generation:" + Generation.getNumberOfGen() + "   ");
        cell_info.setText("   B:" + blueCells.getCellNumber() + "  " + "R:" + redCells.getCellNumber());
    }

    public void displayButtons(Grid grid) {
        for(int i = 0; i < grid.getWidth(); i ++) {
            for(int j = 0; j < grid.getHeight(); j++) {
                if(grid.getCell(i, j).getCellStatus() == CellStatus.RED) {
                    buttons[40 * j + i].setForeground(new Color(255,0,0));
                    buttons[40 * j + i].setText("R");
                }
                else if(grid.getCell(i, j).getCellStatus() == CellStatus.BLUE) {
                    buttons[40 * j + i].setForeground(new Color(0,0,255));
                    buttons[40 * j + i].setText("B");
                }
                else {
                    buttons[40 * j + i].setForeground(new Color(255,255,255));
                    buttons[40 * j + i].setText("");
                }
            }
        }
    }


    /** make all buttons unable to choose, declare the winner on top panel */
    public void declareWinner(Player player){
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
        title.setFont(new Font("Sans Serif", Font.BOLD, 60));
        title.setText("Player " + player.getName() + " wins the game!");
    }

    /** use button id to set the corresponding cell status on grid*/


    /** make other button unable to choose (get rid of duplicate choices)*/
    public void unableOtherButton(String color){

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
    public void setRedTitle(){
        title.setText("RED Player's Turn");
    }

    public void setBlueTitle(){
        title.setText("BLUE Player's Turn");
    }

    public JButton[] getButtons(){
        return buttons;
    }

}
