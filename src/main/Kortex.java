package main;

import fsm.StateMachine;
import states.*;
import config.ConfigurationManager;


/**
 * Created by Vince on 14/01/2016
 */
public class Kortex {
    public static void main(String[] args)
    {
        ConfigurationManager.getInstance().update();
        StateMachine.getInstance().addState(new Game());
        StateMachine.getInstance().addState(new MainMenu());
        StateMachine.getInstance().addState(new Pause());
        StateMachine.getInstance().addState(new HighScore());
        StateMachine.getInstance().addState(new Options());
        StateMachine.getInstance().setState("MAIN_MENU");

        while(Window.getInstance().getGameWindow().isOpen()) {

            StateMachine.getInstance().updateCurrentState();

            //Clear the buffer of any previous renderings
            Window.getInstance().getGameWindow().clear();

            StateMachine.getInstance().renderCurrentState();

            // Update the display with any changes
            Window.getInstance().getGameWindow().display();
        }
    }
}
