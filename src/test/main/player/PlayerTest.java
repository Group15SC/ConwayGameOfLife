package main.player;

import main.board.CellStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player testPlayer = new Player("testPlayer", "R");

    @Test
    void getName() {
        assertEquals("testPlayer", testPlayer.getName());
    }

    @Test
    void getColorRed() {
        assertEquals(CellStatus.RED, testPlayer.getColor());
    }

    @Test
    void getColorBlue(){
        Player testPlayer2 = new Player("testPlayer2", "B");
        assertEquals(CellStatus.BLUE, testPlayer2.getColor());
    }
}