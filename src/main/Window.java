package main;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;

/**
 * Created by Vince on 14/01/2016.
 */
public class Window {
    private static Window instance = null;
    private RenderWindow gameWindow;
    private int screenWidth;
    private int screenHeight;
    private Clock clock;

    private Window(int screenWidth, int screenHeight, String title){
        gameWindow = new RenderWindow(new VideoMode(screenWidth,screenHeight),title);
        gameWindow.setFramerateLimit(30); // Avoid excessive updates, 30fps limit which can be changed later
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        clock=new Clock();
    }

    public static Window getInstance() {
        if( instance == null )
        {
            instance = new Window(640,480,"Kortex"); //Alter window specifics
        }

        return instance;
    }

    public RenderWindow getGameWindow(){
        return gameWindow;
    }

    //Get screen width
    public int getScreenWidth(){
        return screenWidth;
    }

    //get screen height
    public int getScreenHeight() {
        return screenHeight;
    }

    public long getElapsedTime(){
        return clock.getElapsedTime().asMilliseconds();
    }
    public void resetClock(){
        clock.restart();
    }
}
