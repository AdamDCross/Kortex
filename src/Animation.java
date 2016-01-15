import org.jsfml.system.Vector2f;

import java.util.Vector;

/**
 * Created by Vince on 15/01/2016.
 */
public class Animation implements Render{
    private Vector<Image> frames;
    private int noOfFrames;
    private int currentFrame;
    private int delay;

    public Animation(String baseFilePath, String type, int noOfFrames, int delay, Vector2f position){ //type would be ".gif" for example
        frames = new Vector<Image>(noOfFrames);



        this.noOfFrames = noOfFrames;
        currentFrame = 0;
        this.delay = delay;

        //Initialises the frames and relies on padded filename
        for(int i = 0; i < noOfFrames; i++){
            frames.addElement( new Image(baseFilePath + i + type, position) );
            //System.out.println(baseFilePath + i);
        }
    }

    @Override
    public void update() {
        try {
            Thread.sleep(delay);

            currentFrame++;

            if(currentFrame == noOfFrames){
                currentFrame = 0;
            }
        } catch (Exception e){
            //catch something here
        }
    }

    @Override
    public void render() {
        frames.elementAt(currentFrame).render();
    }
}
