package graphics;

import main.Render;
import main.Window;
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

    public Animation(String filePath,int w,int h,int row,int col, int delay, Vector2f position,int startFrame){
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
    }

    @Override
    public void update() {
        int oldFrame=currentFrame;
        currentFrame=(int)(((Window.getInstance().getElapsedTime()+offset*delay)-start)/delay)%noOfFrames;
        if(oldFrame!=currentFrame) {
            int tu = w * (currentFrame % row);
            int tv = h * (currentFrame / row);
            sprite.setTextureRect(new IntRect(tu, tv, w, h));
        }
    }

    public void setPosition(Vector2f pos){
        this.pos=pos;
        sprite.setPosition(pos);
    }

    @Override
    public void render() {
        Window.getInstance().getGameWindow().draw(sprite);
    }
}