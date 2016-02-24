package main;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
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

    public static void drawLine(Vector2f startPos, Vector2f endPos, Color col){
        Window.getInstance().getGameWindow().draw(
                new Vertex[]{new Vertex(startPos,col), new Vertex(endPos,col)}, PrimitiveType.LINE_STRIP);
    }

    public static void drawRect(FloatRect rectCoordinates){
        drawLine(new Vector2f(rectCoordinates.left, rectCoordinates.top), new Vector2f(rectCoordinates.left + rectCoordinates.width, rectCoordinates.top));
        drawLine(new Vector2f(rectCoordinates.left, rectCoordinates.top + rectCoordinates.height), new Vector2f(rectCoordinates.left + rectCoordinates.width, rectCoordinates.top + rectCoordinates.height));
        drawLine(new Vector2f(rectCoordinates.left, rectCoordinates.top), new Vector2f(rectCoordinates.left, rectCoordinates.top + rectCoordinates.height));
        drawLine(new Vector2f(rectCoordinates.left + rectCoordinates.width, rectCoordinates.top), new Vector2f(rectCoordinates.left + rectCoordinates.width, rectCoordinates.top + rectCoordinates.height));
    }
}
