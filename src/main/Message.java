package main;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Class for showing text messages to the player.
 */
public class Message {
    private Font sansRegular;
    public static final String FontFile = "src/assets/LucidaSansRegular.ttf";
    private int fontSize;
    private String JavaVersion;
    private String JdkFontPath;
    private String JreFontPath;
    private Text text;

    /**
     * Constructor for the Message class takes in
     * @param msg This is the  string that is the Message to be displayed
     * @param style This is an int that specificies the style of the text
     * @param position This is a Vector2f that specifies where the text is
     * @param c This specifies the Colour of the text
     */
    public Message(String msg, int style, Vector2f position, Color c, int fontSize){
        sansRegular = new Font();

        this.fontSize = fontSize;

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

    //Setup a message to appear within bounds
    public Message(String msg, int style, FloatRect bounds, Color c, int fontSize){
        sansRegular = new Font();

        this.fontSize = fontSize;

        try {
            sansRegular.loadFromFile(
                    Paths.get(FontFile));
        } catch (IOException ex) {
            ex.printStackTrace( );
        }

        text = new Text(msg, sansRegular, fontSize);
        text.setStyle(style);
        text.setColor(c);

        FloatRect textBounds = text.getLocalBounds( ); //error in calculation when text spans more than one line, needs altering

        Vector2f topLeftText = new Vector2f(textBounds.left, textBounds.top);
        Vector2f bottomRightText = new Vector2f(textBounds.left+textBounds.width, textBounds.top+ textBounds.height);

        Vector2f topLeftBounds = new Vector2f(bounds.left, bounds.top);
        Vector2f bottomRightBounds = new Vector2f(bounds.left+bounds.width, bounds.top+ bounds.height);

        float paddingX = ((bottomRightBounds.x - topLeftBounds.x) - (bottomRightText.x - topLeftText.x)) / 2;
        float paddingY = ((bottomRightBounds.y - topLeftBounds.y) - ((bottomRightText.y - topLeftText.y)*2)) / 2;

        text.setPosition(new Vector2f(bounds.left + paddingX, bounds.top - paddingY ) );

        //check to see if scaling is needed
    }

    /**
     * This function is similar to the constructior and displays a string on text
     * @param str This is the  string that is the Message to be displayed
     * @param c This specifies the Colour of the text
     * @param style This is an int that specificies the style of the text
     * @param position This is a Vector2f that specifies where the text is
     */
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

    /**
     * This function allows user to set the text to be displayed
     * @param str This is the String that the text would be set to
     */
    public void setText(String str){
        text.setString(str);

        FloatRect textBounds = text.getLocalBounds( );
        // Find middle and set as origin/ reference point
        text.setOrigin(textBounds.width / 2,
                textBounds.height / 2);
    }

    /**
     * This draws the text on screen
     */
    public void renderText(){
        Window.getInstance().getGameWindow().draw(text);
    }
}
