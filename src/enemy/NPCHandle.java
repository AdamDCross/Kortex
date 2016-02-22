package enemy;

import main.GameMaths;
import main.Render;
import org.jsfml.system.Vector2f;
import player.Player;
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
    private Player player;
    private long totalXPForEnemyKills;
    private long totalAttackScore;
    private static NPCHandle instance=null;

    /*public NPCHandle(Player player){
        //this.player = player;
        totalXPForEnemyKills = 0;
        totalAttackScore = 0;
        enemies=new ArrayList<>();
        turrets=new ArrayList<>();
    }*/

    private NPCHandle(){
        //this.player = player;
        totalXPForEnemyKills = 0;
        totalAttackScore = 0;
        enemies=new ArrayList<>();
        turrets=new ArrayList<>();
    }

    public static NPCHandle getInstance(){
        if( instance == null ){
            instance = new NPCHandle();
        }

        return instance;
    }

    public void setPlayer(Player p){
        player=p;
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
        //TODO enemy spawning

        //updates all enemies
        for(Enemy e:enemies){
            e.update();
        }
        for(Turret t:turrets){
            t.update();
            t.setXPMultiplier(player.getXPMultiplier());

            totalAttackScore += t.getAttackScore();
            t.resetAttackScore();

            totalXPForEnemyKills += t.getXPForEnemyKills();
            t.resetXP();
        }
        player.increaseScoreBy(totalAttackScore);
        totalAttackScore = 0;

        player.increaseXPBy(totalXPForEnemyKills);
        totalXPForEnemyKills = 0;
    }

    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    public void addTurret(Turret e){
        turrets.add(e);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public boolean turretShoot(Turret t){
        /*TODO
        Steps
        0 -> check target is in range
        0.1 -> if in range, shoot and wait
        1 -> cycle through enemies
        2 -> check distance to range
        5 -> if in range, shoot and wait
                 */

        /*if(!(t.getTarget() ==null&&t.getTarget().getState())){
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
        }*/

        try{
            if(t.getTarget().damage(t.getAtt())&&(t.getTarget().getState())){
                t.setTarget(null);
                return true;
            }
        }catch(NullPointerException n){
            for (Enemy e : enemies) {
                //find magnitude
                float mag= GameMaths.mag(Vector2f.sub(e.getPos(),t.getPos()));
                if(mag<t.getRange()){
                    //this is new target
                    t.setTarget(e);
                    if(e.damage(t.getAtt())&&(t.getTarget().getState())){
                        t.setTarget(null);
                        return true;
                    }
                    break;
                }
            }

        }
        return false;
    }
}