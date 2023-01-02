package main.view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class InteractionMessage implements IMessage{

    /* we don't use class player here, trying to avoid get class from model package involved
     *  to keep the independence */
    private String player1_name;
    private String player2_name;
    private String player1_color = "R"; // player1 by default is the red player
    private String player2_color = "B"; // player2 by default is the blue player
    private ImageIcon icon;
    private Image newImage;
    private ImageIcon newIcon;

    public InteractionMessage(){
    }


    /**
     * set up the welcome messages and some simply instructions
     */
    @Override
    public void setUpMessage(){
        icon = new ImageIcon("src/logo.png");
        Image image = icon.getImage();
        newImage = image.getScaledInstance(150,150, java.awt.Image.SCALE_SMOOTH);
        newIcon = new ImageIcon(newImage);
        showMessage("<html>Welcome to Conway's Game of Life!<br/>This is a Two-Player version of the game, " +
                "so please enter the names of two players one by one.<br/>" +
                "In this game, each player would be assigned a color (red or blue)"+
                        " to represent his/her side, the first player input his/her name has the right to pick a color.<br/>"+
                        " We have some instruction for each input. Simply follow the guide and you are good to go.<br/>" +
                "Let's begin.<html>");

        this.player1_name = handleName(1);
        handleColor();
        showMessage("<html>Thank you " + player1_name +
                ", you are the " + colorConverter(player1_color) + " side<html>");

        String otherName = handleName(2);
        this.player2_name = handleSameName(otherName);
        showMessage("<html>Thank you " + player2_name +
                ", you have been assigned " + colorConverter(player2_color) + " side<html>");

        showMessage("<html>Here's some information about the rules:<br/>"+
                    "The order of turns are decided by names alphabetically. You will find who takes the first turn after closing this window.<br/>"+
                    "In each turn, you must kill one enemy cell and must change one empty cell to your own color (order doesn't matter here).<br/>" +
                    "After each turn, the Life cells go through one generation, and the turn moves to the next player.<br/>"+
                    "The first player who kills all enemy cells will be the winner.<br/>"+
                    "Game starts now! Have fun! :)<html>");
    }

    /**
     * set up the display of input windows
     * @param textMessage: info to show on the window
     */
    private void showMessage(String textMessage) {
        JPanel panel = new JPanel();
        JLabel logo = new JLabel(newIcon);
        JLabel label = new JLabel(textMessage);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Sans Serif", Font.BOLD, 14));
        panel.add(logo);
        panel.add(label,BorderLayout.EAST);
        logo.setPreferredSize(new Dimension(150,200));
        label.setPreferredSize(new Dimension(400,300));
        JOptionPane.showMessageDialog(null, panel, "Conway's Game of Life", JOptionPane.DEFAULT_OPTION);
    }

    /**
     * convert R to red, B to blue, for display purpose only
     * @param shortcut: R or B
     * @return String: red or blue
     */
    public static String colorConverter(String shortcut){
        if(Objects.equals(shortcut, "R")){
            return "red";
        }
        if(Objects.equals(shortcut, "B")){
            return "blue";
        }
        return null;
    }

    /**
     * get player i's name, handle invalid input
     * @param playerId: 1st/2nd player
     * @return String: the valid name of the player
     */
    @Override
    public String handleName(int playerId) {
        String playerName = getInput("<html>Player "+ playerId +", please enter your name below. " +
                "The order of players are decided by name alphabetically, so in order to find out who should take the first step, " +
                "the two names cannot be the same.<br/>"+
                "Be aware that we only accept names start with character :)<html>");

        while(playerName.length() < 1){
            playerName = getInput("<html>Sorry, we need to know your name first.<br/>" +
                    "Please enter a valid name:<html>");
        }
        while(!Character.isLetter(playerName.charAt(0))) {
            playerName = getInput("<html>Sorry, we only accept names starts with character :(<br/>" +
                    "Please try again!<html>");
        }
        return playerName;
    }

    /**
     * get player's chosen color, handle invalid input
     */
    @Override
    public void handleColor() {
        String color = getInput("<html>Hello " + player1_name + "!<br/>" +
                "Please choose a color between red and blue.<br/>" +
                "Enter R for red, enter B for blue.<html>");
        while(color.length() < 1){
            color = getInput("<html>Please choose a color between red and blue to represent your side. <br/>"+
                    "Enter R for red, enter B for blue.<html>");
        }
        while(!color.equals("R") && !color.equals("B")){
            color = getInput("<html>Sorry, you can only choose between red and blue :(<br/>" +
                    "Please enter R for red and B for blue.<html>");
        }
        player1_color = color;
        if(Objects.equals(color, "B")){
            player2_color = "R";
        }
    }


    /**
     * set the name of the red side player
     * @param redName: name of player(String)
     */
    @Override
    public void setRedPlayerName(String redName) {
        switch (player1_color) {
            case "R" -> player1_name = redName;
            case "B" -> player2_name = redName;
        }
    }

    /**
     * set the name of the red side player
     * @param blueName: name of player(String)
     */
    @Override
    public void setBluePlayerName(String blueName) {
        switch (player1_color) {
            case "R" -> player2_name = blueName;
            case "B" -> player1_name = blueName;
        }
    }

    /**
     * ask for the input with JOption
     * @param text: message to show on the window
     * @return String: pass the user input
     */
    @Override
    public String getInput(String text) {
        JPanel panel = new JPanel();
        JLabel logo = new JLabel(newIcon);

        JLabel label = new JLabel(text);
        label.setFont(new Font("Sans Serif", Font.BOLD, 15));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(logo,BorderLayout.CENTER);
        panel.add(label,BorderLayout.EAST);

        logo.setPreferredSize(new Dimension(150,200));
        label.setPreferredSize(new Dimension(400,200));
        String choice = JOptionPane.showInputDialog(null, panel,"Input", JOptionPane.DEFAULT_OPTION);
        if(choice==null){
           choice = "";
        }
        return choice;
    }

    /**
     * @return String: player's name of red side
     */
    @Override
    public String getRedPlayerName(){

        if(player1_color.equals("R")){
            return this.player1_name;
        }
        return this.player2_name;

    }

    /**
     * @return String: blue player's name
     */
    @Override
    public String getBluePlayerName(){

        if(player1_color.equals("B")){
            return this.player1_name;
        }
        return this.player2_name;

    }

    /**
     * handle the case if the two player input the same name (then can't decide the order)
     * @param player2Name: name of the second player's input
     * @return String: the name of the second player, different from the existing one
     */
    @Override
    public String handleSameName(String player2Name){
        while(player2Name.equals(player1_name)){
            player2Name = getInput("<html>Sorry, the name is taken by other player.<br/>" +
                    "Please enter a different one.<html>");
        }
        return player2Name;
    }

    /**
     * display the winner info with pop up
     * @param winnerName: winner's name
     */
    @Override
    public void displayWinnerMessage(String winnerName) {
        showMessage("<html>Congratulations! " + winnerName + " wins the Game!<html>");
    }

    /**
     * set warning message for player's invalid action
     */
    @Override
    public void warnKillSelfCell(){
        JOptionPane.showMessageDialog(null, "Nah, you are killing your own cell",
                "Trying to kill your self!", JOptionPane.WARNING_MESSAGE);

    }

}

