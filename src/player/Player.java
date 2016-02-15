package player;

import main.HighScoreManager;
import main.Score;
import org.jsfml.system.Vector2i;

import java.util.Vector;

/**
 *
 */
public class Player {
    private Score playerScore;
    private long playerXP;
    private long playerScrap;

    private int XPMultiplier;
    Vector<Vector2i> multiplierTiers;
    private int currentTier;

    public Player(String playerName, int startingScrap){
        playerScore = new Score(playerName, 0);
        playerXP = 0;
        playerScrap = startingScrap;
        XPMultiplier = 1;

        //Stored in Vector2i in the form (scrapCostToPerformResearch, XPMultiplier)
        multiplierTiers = new Vector<>(5);
        multiplierTiers.add(new Vector2i(30, 2));
        multiplierTiers.add(new Vector2i(100, 3));
        multiplierTiers.add(new Vector2i(250, 4));
        multiplierTiers.add(new Vector2i(500, 6));

        currentTier = 0;
    }

    public void gameQuit(){
        //Add score to high score manager here for writing to file
        HighScoreManager.getInstance().addScore(playerScore.name, playerScore.score);
    }

    public void performResearch(){
        currentTier++;

        if(currentTier < multiplierTiers.size()){
            Vector2i tier = multiplierTiers.elementAt(currentTier);

            if(playerScrap >= tier.x){
                playerScrap -= tier.x;
                XPMultiplier = tier.y;
                playerXP += 10 * XPMultiplier;
            }else{
                currentTier--;
            }
        }
    }

    //Will return a bool to indicate if it is possible or not.
    //if it returns false, the player's scrap count remains the same
    //if it returns true, the player's scrap is reduced and the go ahead
    //is given for the turret to be purchased
    public boolean purchaseTurret(int scrapCost){
        if(playerScrap >= scrapCost){
            playerScrap -= scrapCost;
            return true;
        }

        return false;
    }

    public int getCurrentResearchLevel(){
        return currentTier;
    }

    public int getXPMultiplier(){
        return XPMultiplier;
    }

    public void increaseScoreBy(long value){
        playerScore.score += value;
    }

    public void increaseScrapBy(long value){
        playerScrap += value;
    }

    public void increaseXPBy(long value){
        playerXP += value;
    }
}
