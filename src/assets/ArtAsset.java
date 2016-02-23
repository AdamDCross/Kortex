package assets;

import graphics.Image;
import org.jsfml.system.Vector2f;

/**
 *
 */
public class ArtAsset extends Asset{
    private Image asset;

    public ArtAsset(String assetType, String assetID, String assetPath){
        super(assetType,assetID,assetPath);

        asset = new Image(assetPath, new Vector2f(0.0f,0.0f));
    }

    public Image getImage(){
        return asset;
    }
}
