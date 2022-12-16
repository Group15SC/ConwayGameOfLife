package main.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player testPlayer;

    @BeforeEach
    void setUp(){
        testPlayer = new Player("A", "R");
    }

    @Test
    void getName() {
        assertEquals("A", testPlayer.getName());
    }

    @Test
    void getColor() {
        assertEquals("R", testPlayer.getColor());
    }

    @Test
    void testDefaultCells() {
        /** need to update after deciding the initial cell pattern */
        assertEquals(0, testPlayer.getNumberOfCells());
    }

    @Test
    void testAddCells() {
        testPlayer.setNumberOfCells(5);
        assertEquals(5,testPlayer.getNumberOfCells());
    }
}