package main.game;

import main.player.Player;
import main.ui.Input;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Game {

    private List<Player> players = new ArrayList<>();

    private String getDecidedColor(String otherColor){
        if(otherColor.equals("R")){
            return "B";
        }
        else return "R";
    }


    public void setUp(){
        /**
         * only added the player part
         * still need to fill with variables from board package!
         */
        Input input = new Input();

        // player 1
        String player1Name = input.getPlayerName();
        String player1Color = input.getPlayerColor();
        players.add(new Player(player1Name, player1Color));

        // player 2
        String player2Name = input.getPlayerName();
        String player2Color = getDecidedColor(player1Color); // player2's color is decided by player1's color
        players.add(new Player(player2Name, player2Color));

        // sort the list of players alphabetically
        players.sort(Comparator.comparing(Player::getName));
    }

}
