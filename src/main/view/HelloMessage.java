package main.view;

import main.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HelloMessage {

    private final String player1_name;
    private final String player2_name;
    private String player1_color;
    private String player2_color;
    ImageIcon icon;
    Image newimage;
    ImageIcon newIcon;
    public HelloMessage(){
        icon = new ImageIcon("logo.png");
        Image image = icon.getImage();
        newimage= image.getScaledInstance(150,150, java.awt.Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(newimage);
        showMessage("<html>Welcome to Conway's Game of Life!<br/>This is a 2 player version game, " +
                "so please enter the names of two players one by one.<br/>" +
                "Please be aware that we only accept names start with character :)<html>");

        this.player1_name = handleName(1);
        handleColor();
        showMessage("Thank you " + player1_name +
                ", you are the " + colorConverter(player1_color) + " side");

        String anotherName = handleName(2);
        this.player2_name = handleSameName(anotherName);
        showMessage("Thank you " + player2_name +
                ", you are the " + colorConverter(player2_color) + " side");

        showMessage("Game starts now!");
    }

    private void showMessage(String textMessage) {
        JPanel panel = new JPanel();
        JLabel logo = new JLabel(newIcon);
        String welcome = textMessage;
        JLabel label = new JLabel(welcome);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Sans Serif", Font.BOLD, 15));
        panel.add(logo);
        panel.add(label,BorderLayout.EAST);
        logo.setPreferredSize(new Dimension(150,300));
        label.setPreferredSize(new Dimension(500,300));
        JOptionPane.showMessageDialog(null, panel, "Conway's Game of Life", JOptionPane.DEFAULT_OPTION);
    }

    /** convert R to red, B to blue
     *  for display purpose only */
    private String colorConverter(String shortcut){
        if(Objects.equals(shortcut, "R")){
            return "red";
        }
        if(Objects.equals(shortcut, "B")){
            return "blue";
        }
        return null;
    }

    /** get player's chosen color, handle invalid input*/
    private void handleColor() {
        String color = getInput("<html>Hello " + player1_name + "!<br/>" +
                "Please choose a color between red and blue.<br/>" +
                "Enter R for red, enter B for blue.<html>");
        while(color.length() < 1){
            color = getInput("<html>Please choose a color between red and blue to represent your side. <br/>"+
                    "Enter R for red, enter B for blue.<html>");
        }
        while(!"[RB]".contains(color)){
            color = getInput("<html>Sorry, you can only choose between red and blue :(<br/>" +
                    "Please enter R for red and B for blue.<html>");
        }
        player1_color = color;
        if(Objects.equals(color, "R")){
            player2_color = "B";
        }
        else{
            player2_color = "R";
        }
    }

    /** get player i's name, handle invalid input*/
    private String handleName(int i) {
        String name = getInput("<html>Welcome to Conway's Game of Life!<br/>This is a 2 player version game, " +
                "so please enter the names of two players one by one.<br/>" +
                "Please be aware that we only accept names start with character :)<html>");

        while(name.length() < 1){
            name = getInput("<html>Sorry, we need to know your name first.<br/>" +
                    "Please enter a  valid name:<html>");
        }
        while(!Character.isLetter(name.charAt(0))) {
            name = getInput("<html>Sorry, we only accept names starts with character :(<br/>" +
                    "Please try again!<html>");
        }
        return name;

    }

    private String getInput(String text) {
        JPanel panel = new JPanel();
        JLabel logo = new JLabel(newIcon);
        String askName = text;

        JLabel label = new JLabel(askName);
        label.setFont(new Font("Sans Serif", Font.BOLD, 15));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(logo,BorderLayout.CENTER);
        panel.add(label,BorderLayout.EAST);

        logo.setPreferredSize(new Dimension(150,150));
        label.setPreferredSize(new Dimension(700,150));
        String name = JOptionPane.showInputDialog(null, panel,"Input", JOptionPane.DEFAULT_OPTION);
        return name;
    }

    /** get player's name of red side*/
    public String getRedPlayerName(){

        if(Objects.equals(player1_color, "R")){
            return player1_name;
        }
        return player2_name;

    }

    /** get player's name of blue side*/
    public String getBluePlayerName(){

        if(Objects.equals(player1_color, "B")){
            return player1_name;
        }
        return player2_name;

    }

    private String handleSameName(String name){
        while(name.equals(player1_name)){
            name = getInput("<html>Sorry, the name is taken by other player.<br/>" +
                    "Please enter a different one.<html>");
        }
        return name;
    }

    public void displayWinnerMessage(Player winner) {
        showMessage("Congratulations! " + winner.getName() + " wins the Game!");
    }

}
