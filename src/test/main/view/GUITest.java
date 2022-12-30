package main.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

class GUITest {
    @Test
    void buttons() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(100,100);
            robot.mousePress(MouseEvent.BUTTON1_MASK);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {}
    }




    @Test
    void updateStats() {
    }

    @Test
    void displayButtons() {
    }

    @Test
    void declareWinner() {
    }

    @Test
    void unableOtherButton() {
    }

    @Test
    void setButtonFree() {
    }

    @Test
    void setRedTitle() {
    }

    @Test
    void setBlueTitle() {
    }

    @Test
    void getButtons() {
    }

    @Test
    void updateButtons() {
    }
}
