package assets;


import org.jsfml.audio.SoundSource;

import java.util.HashMap;



public class AudioManager {
    private static AudioManager instance = null;
    //TODO check if there is a better way at implementing this with the play function
    private HashMap<String,SoundSource> tracks;
    private SoundSource bcgMusic;

    private AudioManager(){
        bcgMusic=null;
        tracks=new HashMap<String, SoundSource>();
    }
    public static AudioManager getInstance() {
        if( instance == null )
        {
            instance = new AudioManager();
        }

        return instance;
    }

    private void addMusic(String key, SoundSource snd){
        tracks.put(key,snd);
    }

    private void setTrackToBackground(String key){
        bcgMusic=tracks.get(key);
        if(bcgMusic!=null){
            //
        }
    }
}
