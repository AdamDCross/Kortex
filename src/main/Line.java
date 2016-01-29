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

    public static void drawRect(Vector2f topLeftCoordinates, Vector2f bottomRightCoordinates){
        drawLine(new Vector2f(topLeftCoordinates.x, topLeftCoordinates.y), new Vector2f(bottomRightCoordinates.x,topLeftCoordinates.y));
        drawLine(new Vector2f(topLeftCoordinates.x, bottomRightCoordinates.y), new Vector2f(bottomRightCoordinates.x, bottomRightCoordinates.y));
        drawLine(new Vector2f(topLeftCoordinates.x, topLeftCoordinates.y), new Vector2f(topLeftCoordinates.x, bottomRightCoordinates.y));
        drawLine(new Vector2f(bottomRightCoordinates.x, topLeftCoordinates.y), new Vector2f(bottomRightCoordinates.x, bottomRightCoordinates.y));
    }
}
