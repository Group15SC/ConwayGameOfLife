//package main.ui;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class InputTest {
//
//
//    private final InputStream stdin = System.in;
//    private void setInput(String input) {
//        System.setIn(new ByteArrayInputStream(input.getBytes()));
//    }
//
//    @AfterEach
//    public void restoreSystemIn() {
//        System.setIn(stdin);
//    }
//
//
//    @Test
//    void testGetPlayerName() {
//        setInput("Bob");
//        Input input = new Input();
//        assertEquals("Bob", input.getPlayerName());
//    }
//
//    @Test
//    void testInputPlayerNameNotInALine() {
//        setInput("\nBob"); //empty first
//        Input input = new Input();
//        assertEquals("Bob", input.getPlayerName());
//    }
//
//    @Test
//    void testEnterRed() {
//        setInput("R");
//        Input input = new Input();
//        assertEquals("R", input.getPlayerColor());
//    }
//
//    @Test
//    void testInvalidInputColorFirst() {
//        // first enter an invalid input "Y", then input a valid input "R". Should get R as result
//        setInput("Y\nR");
//        Input input = new Input();
//        assertEquals("R", input.getPlayerColor());
//    }
//
//}