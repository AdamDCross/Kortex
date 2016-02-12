package main;

import org.jsfml.graphics.Image;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.View;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;

import java.io.IOException;
import java.nio.file.Paths;

public class Window {
    private static Window instance = null;
    private RenderWindow gameWindow;
    private int screenWidth;
    private int screenHeight;
    private Clock clock;

    private Window(int screenWidth, int screenHeight, String title){
        this.gameWindow = new RenderWindow(new VideoMode(screenWidth,screenHeight),title);
        this.gameWindow.setFramerateLimit(30); // Avoid excessive updates, 30fps limit which can be changed later
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        try {
            Image tmp = new Image();
            tmp.loadFromFile(Paths.get("graphics/icon/hazard.jpg"));
            gameWindow.setIcon(tmp);
        }
        catch(IOException e){
            System.out.println("error");
        }

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
