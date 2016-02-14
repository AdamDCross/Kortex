package main;

import java.util.Vector;

/**

 */
public class HighScoreManager {
    private static HighScoreManager instance = null;
    Vector<Score> highScores;

    private HighScoreManager(){
        highScores = new Vector<>(10);

        addScore("Phil",1456);
        addScore("Vince",1234);
        addScore("Adam",877);
        addScore("Tom",3);
        addScore("Tesfa",0);
    }

    public static HighScoreManager getInstance(){
        if(instance == null){
            instance = new HighScoreManager();
        }

        return instance;
    }

    public void addScore(String name, long score){
        if(highScores.size() == 0){
            highScores.addElement(new Score(name,score));
        }
        else {
            for (int i = 0; i < highScores.size(); i++) {
                if(score > highScores.elementAt(i).score)
                {
                    highScores.add(i, new Score(name, score));
                    break;
                }
                else{
                    highScores.addElement(new Score(name, score));
                }
                break;
            }
        }
    }



    public Vector<Score> getHighScores(){
        return highScores;
    }
}
