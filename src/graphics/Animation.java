package graphics;

import main.Render;
import main.Window;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.nio.file.Paths;

/**
 * Animation Class
 * Renders the sprite and assigns a position to it.
 */
public class Animation implements Render {
    private Sprite sprite;
    private Texture tex;
    private int noOfFrames;
    private int currentFrame;
    private int delay;
    private int w,h,col,row,offset;
    private long start;
    private Vector2f pos;
    private boolean visible;
    private boolean repeat;
    private boolean ended;
    private String filePath;

    public Animation(String filePath,int w,int h,int row,int col, int delay, Vector2f position,int startFrame, boolean repeat){
        this.filePath = filePath;
        pos=position;
        noOfFrames=row*col;
        currentFrame = startFrame%noOfFrames;
        offset=startFrame;
        this.delay = delay;
        tex= new Texture();
        this.h=h;
        this.w=w;
        this.row=row;
        this.col=col;
        start=Window.getInstance().getElapsedTime();
        try{
            tex.loadFromFile(Paths.get(filePath));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        int tu = w * (currentFrame % row);
        int tv = h * (currentFrame / row);
        sprite=new Sprite(tex,new IntRect(tu,tv,w,h));
        sprite.setPosition(pos);

        visible = true;
        this.repeat = repeat;
        ended = false;
    }

    //Alternative constructor which scales an animation to a particular set of dimensions
    public Animation(String filePath,int w,int h,int row,int col, int delay, Vector2f position,int startFrame, FloatRect dimensions, boolean repeat){
        this.filePath = filePath;
        pos=position;
        noOfFrames=row*col;
        currentFrame = startFrame%noOfFrames;
        offset=startFrame;
        this.delay = delay;
        tex= new Texture();
        this.h=h;
        this.w=w;
        this.row=row;
        this.col=col;
        start=Window.getInstance().getElapsedTime();
        try{
            tex.loadFromFile(Paths.get(filePath));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        int tu = w * (currentFrame % row);
        int tv = h * (currentFrame / row);
        sprite=new Sprite(tex,new IntRect(tu,tv,w,h));
        sprite.setPosition(pos);

        if( getSprite().getLocalBounds().width < dimensions.width
                || getSprite().getLocalBounds().height < dimensions.height
                || getSprite().getLocalBounds().width > dimensions.width
                || getSprite().getLocalBounds().height > dimensions.height) {

            getSprite().setOrigin(0.0f, 0.0f);
            getSprite().setScale(dimensions.width / getSprite().getLocalBounds().width,
                    dimensions.height / getSprite().getLocalBounds().height);
        }

        visible = true;
        this.repeat = repeat;
        ended = false;
    }

    public int getDelay(){
        return delay;
    }

    public int getCol(){
        return col;
    }

    public int getRow(){
        return row;
    }

    public int getHeight(){
        return h;
    }

    public int getWidth(){
        return w;
    }

    public String getFilePath(){
        return filePath;
    }

    @Override
    public void update() {
        if(visible && !ended) {
            int oldFrame = currentFrame;
            currentFrame = (int) (((Window.getInstance().getElapsedTime() + offset * delay) - start) / delay) % noOfFrames;
            if (oldFrame != currentFrame) {
                int tu = w * (currentFrame % row);
                int tv = h * (currentFrame / row);
                sprite.setTextureRect(new IntRect(tu, tv, w, h));
            }

            if(currentFrame == (noOfFrames-1) && !repeat){
                ended = true;
            }
        }
    }

    public void setPosition(Vector2f pos){
        this.pos=pos;
        sprite.setPosition(pos);
    }

    @Override
    public void render() {
        if(visible && !ended) {
            Window.getInstance().getGameWindow().draw(sprite);
        }
    }

    public void toggleVisibility(){
        visible = !visible;
    }

    public Sprite getSprite(){
        return sprite;
    }
}