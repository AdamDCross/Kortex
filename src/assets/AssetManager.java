package assets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 */
public class AssetManager {
    private static AssetManager instance = null;
    private Vector<Asset> assets;

    public static AssetManager getInstance() {
        if(instance == null){
            instance = new AssetManager();
        }

        return instance;
    }

    private AssetManager() {
        String assetCSV = "src/assets/assets.csv";
        assets = new Vector<Asset>(25);

        try {
            BufferedReader r=new BufferedReader(new FileReader(assetCSV));

            String buff;
            while((buff=r.readLine())!=null) {
                String[] mapS = buff.split("[,]");

                assets.addElement(new ArtAsset(mapS[0], mapS[1], mapS[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector<ArtAsset> getArtAssetByAssetType(String assetType){
        Vector<ArtAsset> tmp = new Vector<ArtAsset>(5);

        for(int i = 0; i < assets.size(); i++){
            if(assets.elementAt(i).getAssetType().equals(assetType)){
                tmp.addElement((ArtAsset)assets.elementAt(i));
            }
        }

        return tmp;
    }
}
