package main.controller;

import main.model.Game;
import main.view.HelloMessage;
import main.mockClasses.MockUI;
import org.junit.jupiter.api.Test;

class ControllerTest {

    Game game = new Game();
    MockUI mockUI = new MockUI();
    HelloMessage hello = new HelloMessage();
    Controller controller = new Controller(game, mockUI, hello);

    @Test
    void doSomething(){

    }


}