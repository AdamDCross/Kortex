package main;

import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.Vertex;
import org.jsfml.system.Vector2f;

/**
 * Created by vincentdealmeida on 26/01/2016.
 */
public class Line {
    public static void drawLine(Vector2f startPos, Vector2f endPos){
        Window.getInstance().getGameWindow().draw(
                new Vertex[]{new Vertex(startPos), new Vertex(endPos)}, PrimitiveType.LINE_STRIP);
    }
}
