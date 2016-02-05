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
    private Vector2i screenRes;
    private Clock clock;

    private Window(int screenWidth, int screenHeight, String title){
        this.gameWindow = new RenderWindow(new VideoMode(screenWidth,screenHeight),title);
        this.gameWindow.setFramerateLimit(30); // Avoid excessive updates, 30fps limit which can be changed later
        screenRes = new Vector2i(screenWidth, screenHeight);

        try {
            Image tmp = new Image();
            tmp.loadFromFile(Paths.get("src/graphics/icon/kortex-icon.png"));
            gameWindow.setIcon(tmp);
        }
        catch(IOException e){
            System.out.println("Error: Window class - Icon not found.");
        }

        clock=new Clock();
        
    }

    public static Window getInstance() {
        if( instance == null )
        {
            instance = new Window(1280,1024,"Kortex"); //Alter window specifics
        }

        return instance;
    }

    public void recalculateScreenRes(Vector2i newRes) {
        screenRes = newRes;

        //gameWindow.setSize(newRes); Strange bug moves entire window when this line of code is executed, needs more investigating
        //

    }

    public RenderWindow getGameWindow(){
        return gameWindow;
    }

    //Get screen width
    public int getScreenWidth(){
        return screenRes.x;
    }

    //get screen height
    public int getScreenHeight() {
        return screenRes.y;
    }

    public long getElapsedTime(){
        return clock.getElapsedTime().asMilliseconds();
    }

    public void resetClock(){
        clock.restart();
    }
}
