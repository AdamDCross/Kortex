package enemy;

import main.Cell;
import main.GameMaths;
import main.Render;
import org.jsfml.system.Vector2f;
import player.Turret;
import states.Game;

import java.util.ArrayList;

/**
 * Created by Phillip on 02/02/2016.
 */
public class NPCHandle implements Render {
    private ArrayList<Enemy> enemies;
    private ArrayList<Turret> turrets;
    private Game game;

    NPCHandle(Game game){
        this.game=game;
    }

    @Override
    public void render() {
        for(Enemy e:enemies){
            e.render();
        }
        for(Turret t:turrets){
            t.render();
        }
    }

    @Override
    public void update() {
        //updates all enemies
        for(Enemy e:enemies){
            e.update();
        }
        for(Turret t:turrets){
            t.update();
        }
    }

    public void addEnemy(Enemy e){
        enemies.add(e);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Cell getCell(int x, int y){
        return game.getCell(x,y);
    }

    public void turretShoot(Turret t){
        /*TODO
        Steps
        0 -> check target is in range
        0.1 -> if in range, shoot and wait
        1 -> cycle through enemies
        2 -> check distance to range
        5 -> if in range, shoot and wait
                 */
        if(!(t.getTarget() ==null&&t.getTarget().getState())){
            if(t.getTarget().damage(t.getAtt())){
                t.setTarget(null);
            }
        }else {
            for (Enemy e : enemies) {
                //find magnitude
                float mag= GameMaths.mag(Vector2f.sub(e.getPos(),t.getPos()));
                if(mag<t.getRange()){
                    //this is new target
                    t.setTarget(e);
                    if(e.damage(t.getAtt())){
                        t.setTarget(null);
                    }
                    break;
                }
            }
        }
    }
}
