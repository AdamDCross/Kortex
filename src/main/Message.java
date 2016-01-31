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
    private String FontFile;
    private int fontSize;
    private String JavaVersion;
    private String JdkFontPath;
    private String JreFontPath;
    private String FontPath;
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

        FontFile = "src/assets/LucidaSansRegular.ttf";
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

        FontFile = "src/font/LucidaSansRegular.ttf";
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


        //text.setPosition(new Vector2f(bounds.left, bounds.top));

        FloatRect textBounds = text.getLocalBounds( ); //error in calculation when text spans more than one line, needs altering

        System.out.println("Top left: (" + textBounds.left + "," + textBounds.top + ")");
        System.out.println("Bottom right: (" + (textBounds.left+textBounds.width) + "," + (textBounds.top+ textBounds.height) + ")");

        Vector2f topLeft = new Vector2f(textBounds.left, textBounds.top);
        Vector2f bottomRight = new Vector2f(textBounds.left+textBounds.width, textBounds.top+ textBounds.height);

        float paddingX = ((bounds.left + bounds.width) - (bottomRight.x - topLeft.x)) / 2;
        float paddingY = ((bounds.top + bounds.height) - ((bottomRight.y - topLeft.y) * 2) + fontSize) / 2;

        text.setPosition(new Vector2f(bounds.left + paddingX, bounds.top + paddingY));

        //check to see if scaling is needed

        // Find middle and set as origin/ reference point
        text.setOrigin((bounds.left), (bounds.top));
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
