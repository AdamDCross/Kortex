package main;

import java.io.*;
import java.util.Vector;

/**

 */
public class HighScoreManager {
    private static HighScoreManager instance = null;
    private Vector<Score> highScores;
    private String highScoresCSV;

    private HighScoreManager(){
        highScores = new Vector<>(10);
        highScoresCSV = "src/assets/high_scores.csv";

        //Read all high scores from file
        try {
            BufferedReader r=new BufferedReader(new FileReader(highScoresCSV));

            String buff;
            while((buff=r.readLine())!=null) {
                String[] mapS = buff.split("[,]");

                addScore(mapS[0], Integer.parseInt(mapS[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            boolean added = false;
            for (int i = highScores.size() - 1; i >= 0; i--) {
                if(score > highScores.elementAt(i).score)
                {
                    highScores.add(i, new Score(name, score));
                    added = true;
                    break;
                }
            }

            if(!added) {
                highScores.addElement(new Score(name, score));
            }
        }
    }

    public Vector<Score> getHighScores(){
        return highScores;
    }

    public void writeScoresToFile(){
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(highScoresCSV));

            for(int i = 0; i < highScores.size(); i++){
                if(i == (highScores.size()-1)){
                    writer.write(highScores.elementAt(i).name + "," + highScores.elementAt(i).score);
                    break;
                }

                writer.write(highScores.elementAt(i).name + "," + highScores.elementAt(i).score + "\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
