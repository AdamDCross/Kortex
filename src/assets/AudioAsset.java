package assets;

import org.jsfml.audio.Music;

import java.io.IOException;
import java.nio.file.Paths;

/**

 */
public class AudioAsset extends Asset {
    private Music asset;

    public AudioAsset(String assetType, String assetID, String assetPath){
        super(assetType, assetID, assetPath);
        asset = null;

        if(assetType.equals("MUSIC")){
            asset = new Music();

            try {
                asset.openFromFile(Paths.get(assetPath));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public Music getMusicObject(){
        return asset;
    }
}
