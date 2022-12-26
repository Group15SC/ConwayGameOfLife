package main.game;

import main.board.Grid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerationTest {

    Grid testGrid = new Grid(5,5);

    @Test
    void aGeneration() {
    }

    @Test
    void testGetGeneration(){
        assertEquals(0, Generation.getNumberOfGen());
//        Grid testGrid1 = new Grid();
        Generation gen1 = new Generation(testGrid);
//        gen1.aGeneration();
//        assertEquals(1, Generation.getNumberOfGen());
//        Generation gen2 = new Generation(testGrid);
//        gen2.aGeneration();
//        assertEquals(2, Generation.getNumberOfGen());
    }

}