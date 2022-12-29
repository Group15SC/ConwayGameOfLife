package main.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HelloMessage {

    private final String player1_name;
    private final String player2_name;
    private String player1_color;
    private String player2_color;

    public HelloMessage(){
        ImageIcon icon = new ImageIcon("logo.png");
        Image image = icon.getImage();
        Image newimage = image.getScaledInstance(200,200, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newicon = new ImageIcon(newimage);
        JPanel panel = new JPanel();
        JLabel logo = new JLabel(newicon);
        JLabel text = new JLabel("<html>Welcome to Conway's Game of Life!<br/>This is a 2 player version game, so please enter the names of two players one by one.<br/>Please be aware that we only accept names start with character :)<html>");
        text.setFont(new Font("Ink Free", Font.BOLD, 20));
        panel.add(logo);
        panel.add(text,BorderLayout.EAST);
        logo.setPreferredSize(new Dimension(200,200));
        text.setPreferredSize(new Dimension(800,200));
        JOptionPane.showMessageDialog(null, panel, "Conway's Game of Life", JOptionPane.DEFAULT_OPTION);

        this.player1_name = handleName(1);
        handleColor();
        JOptionPane.showMessageDialog(null, "Thank you " + player1_name +
                ", you are the " + colorConverter(player1_color) + " side");

        this.player2_name = handleName(2);
        JOptionPane.showMessageDialog(null, "Thank you " + player2_name +
                ", you are the " + colorConverter(player2_color) + " side");

        JOptionPane.showMessageDialog(null, "Game starts now!");
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
        String color = JOptionPane.showInputDialog("Hello " + player1_name + "!\n" +
                "Please choose a color between red and blue.\n" +
                "Enter R for red, enter B for blue.");
        while(!"[RB]".contains(color)){
            color = JOptionPane.showInputDialog("Sorry, you can only choose between red and blue :(\n" +
                    "Please enter R for red and B for blue.");
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
    private String handleName(int i){

        String name = JOptionPane.showInputDialog("Player "+ i + ", please enter your name: \n" +
                "Please be aware that we only accept names start with character.");
        while(!Character.isLetter(name.charAt(0))){
            name = JOptionPane.showInputDialog("Sorry, we only accept names starts with character :(\n" +
                    "Please try again!");
        }
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

}
