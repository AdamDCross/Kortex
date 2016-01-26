package actor;

import main.Render;
import org.jsfml.system.Vector2i;

/**
 * Actor class
 */
public class Actor implements Render {
    private Vector2i vel;
    private Vector2i pos;
    private float dir;
    private Render img;

    Actor(Vector2i vel,Vector2i pos,int dir,Render img){
        this.vel=vel;
        this.pos=pos;
        this.dir=dir;
        this.img=img;
    }

    @Override
    public void render() {
        img.render();
    }

    @Override
    public void update() {
        img.update();
        pos=Vector2i.add(vel,pos);
    }

    public Vector2i getVel() {
        return vel;
    }

    public void setVel(Vector2i vel) {
        this.vel = vel;
    }

    public Vector2i getPos() {
        return pos;
    }

    public void setPos(Vector2i pos) {
        this.pos = pos;
    }

    public float getDir() {
        return dir;
    }

    public void setDir(float dir) {
        this.dir = dir;
    }

    public Render getImg() {
        return img;
    }

    public void setImg(Render img) {
        this.img = img;
    }
}
