package player;

import java.util.Vector;

/**
 *
 */
public class TurretManager {
    private Player player;
    private Vector<Turret> turrets;
    private long totalXPForEnemyKills;
    private long totalAttackScore;

    public TurretManager(Player player){
        this.player = player;
        turrets = new Vector<>(5);
        totalXPForEnemyKills = 0;
        totalAttackScore = 0;
    }

    public void update(){
        for(int i = 0; i < turrets.size(); i++){
            turrets.elementAt(i).setXPMultiplier(player.getXPMultiplier());

            totalAttackScore += turrets.elementAt(i).getAttackScore();
            turrets.elementAt(i).resetAttackScore();

            totalXPForEnemyKills += turrets.elementAt(i).getXPForEnemyKills();
            turrets.elementAt(i).resetXP();
        }

        player.increaseScoreBy(totalAttackScore);
        totalAttackScore = 0;

        player.increaseXPBy(totalXPForEnemyKills);
        totalXPForEnemyKills = 0;

    }

    public void render(){

    }
}
