package main.player;

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
    void getColor() {
        assertEquals("R", testPlayer.getColor());
    }

}