package main.ui;

import javax.swing.*;
import java.util.Objects;

public class HelloMessage {

    private final String player1_name;
    private final String player2_name;
    private String player1_color;
    private String player2_color;

    public HelloMessage(){
        ImageIcon logo = new ImageIcon("logo.png");
        JOptionPane.showMessageDialog(null, "Welcome to Conway's Game of Life!\n" +
                "This is a 2 player version game, so please enter the names of two players one by one.\n" +
                "Please be aware that we only accept names start with character :)");

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
