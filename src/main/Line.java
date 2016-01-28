package main;

import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.Vertex;
import org.jsfml.system.Vector2f;

/**
 * Line class for drawing a new line on the screen
 */
public class Line {
    public static void drawLine(Vector2f startPos, Vector2f endPos){
        Window.getInstance().getGameWindow().draw(
                new Vertex[]{new Vertex(startPos), new Vertex(endPos)}, PrimitiveType.LINE_STRIP);
    }
}
