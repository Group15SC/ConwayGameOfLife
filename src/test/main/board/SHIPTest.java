package main.board;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SHIPTest {

    @Test
    void test(){
        Ship ship = new Ship();
        // should test whole set, not done yet
        assertEquals(10, ship.getRedPattern().get(0).getX());
        assertEquals(29, ship.getBluePattern().get(0).getX());
    }

}