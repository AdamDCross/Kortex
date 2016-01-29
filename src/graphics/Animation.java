package graphics;

import main.Render;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * Created by Vince on 15/01/2016.
 */
public class Animation implements Render {
    private Vector<Image> frames;
    private int noOfFrames;
    private int currentFrame;
    private int delay;
    private Vector2f pos;

    /*public Animation(String baseFilePath, String type, int noOfFrames, int delay, Vector2f position){ //type would be ".gif" for example
        frames = new Vector<Image>(noOfFrames);
        pos=position;


        this.noOfFrames = noOfFrames;
        currentFrame = 0;
        this.delay = delay;

        //Initialises the frames and relies on padded filename
        for(int i = 0; i < noOfFrames; i++){
            frames.addElement( new Image(baseFilePath + i + type, position) );
            //System.out.println(baseFilePath + i);
        }
    }*/

    public Animation(String filePath,int w,int h, int noOfFrames, int delay, Vector2f position){ //type would be ".gif" for example
        frames = new Vector<Image>(noOfFrames);
        pos=position;
        this.noOfFrames = noOfFrames;
        currentFrame = 0;
        this.delay = delay;
        org.jsfml.graphics.Image sheet= new  org.jsfml.graphics.Image();
        try{
            sheet.loadFromFile(Paths.get(filePath));
        }
        catch (Exception e){
            e.printStackTrace();
        }


        //Initialises the frames and relies on padded filename
        for(int i = 0; i < noOfFrames; i++){
            org.jsfml.graphics.Image img=new org.jsfml.graphics.Image();
            img.copy(sheet,0,0,new IntRect(w*i,0,w,h));
            frames.addElement( new Image(img, position) );
        }
    }

    public Animation(String filePath,int w,int h,int row,int col, int delay, Vector2f position){
        frames = new Vector<Image>(row*col);
        pos=position;
        noOfFrames=row*col;
        currentFrame = 0;
        this.delay = delay;
        org.jsfml.graphics.Image sheet= new  org.jsfml.graphics.Image();
        try{
            sheet.loadFromFile(Paths.get(filePath));
        }
        catch (Exception e){
            e.printStackTrace();
        }


        //Initialises the frames and relies on padded filename
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++) {
                org.jsfml.graphics.Image img = new org.jsfml.graphics.Image();
                img.copy(sheet, 0, 0, new IntRect(w * r, h*c, w, h));
                frames.addElement(new Image(img, position));
                System.out.println(r+" - "+c);
            }
        }
    }

    @Override
    public void update() {
        try {
            Thread.sleep(delay);
            currentFrame=(currentFrame+1)%noOfFrames;
        } catch (Exception e){
            //catch something here
        }
    }

    public void setPosition(Vector2f pos){
        this.pos=pos;
    }

    @Override
    public void render() {
        frames.elementAt(currentFrame).render();
    }

    public Image getFrame(int i){
        return frames.elementAt(i);
    }
}
