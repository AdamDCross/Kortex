package main;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Vince on 14/01/2016.
 */
public class Message {
    private Font sansRegular;
    private String FontFile;
    private int fontSize;
    private String JavaVersion;
    private String JdkFontPath;
    private String JreFontPath;
    private String FontPath;
    private Text text;

    public Message(String msg, int style, Vector2f position, Color c){
        sansRegular = new Font();

        FontFile = "src/font/LucidaSansRegular.ttf";
        fontSize = 48;

        try {
            sansRegular.loadFromFile(
                    Paths.get(FontFile));
        } catch (IOException ex) {
            ex.printStackTrace( );
        }

        text = new Text(msg, sansRegular, fontSize);
        text.setStyle(style);
        text.setColor(c);
        text.setPosition(position);

        FloatRect textBounds = text.getLocalBounds( ); //error in calculation when text spans more than one line, needs altering
        // Find middle and set as origin/ reference point
        text.setOrigin(textBounds.width / 2,
                textBounds.height / 2);
    }

    public void setText(String str, Color c, int style, Vector2f position) {
        text.setColor(c);
        text.setString(str);
        text.setPosition(position);
        text.setStyle(style);

        FloatRect textBounds = text.getLocalBounds( );
        // Find middle and set as origin/ reference point
        text.setOrigin(textBounds.width / 2,
                textBounds.height / 2);
    }

    public void setText(String str){
        text.setString(str);

        FloatRect textBounds = text.getLocalBounds( );
        // Find middle and set as origin/ reference point
        text.setOrigin(textBounds.width / 2,
                textBounds.height / 2);
    }

    public void renderText(){
        Window.getInstance().getGameWindow().draw(text);
    }
}
