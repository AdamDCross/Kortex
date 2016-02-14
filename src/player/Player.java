package player;

import main.Score;

/**
 *
 */
public class Player {
    private Score playerScore;
    private long playerXP;
    private long playerScrap;
    private String playerName;

    public Player(String playerName, int startingScrap){
        this.playerName = playerName;
        playerScore = new Score(playerName, "Today", 0);
        playerXP = 0;
        playerScrap = startingScrap;
    }

    public void update() {

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
