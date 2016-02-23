package assets;

/**
 *
 */
public class Asset {
    private String assetType;
    private String assetID;
    private String assetPath;

    public Asset(String assetType, String assetID, String assetPath){
        this.assetType = assetType;
        this.assetID = assetID;
        this.assetPath = assetPath;
    }

    public String getAssetType(){return assetType;}
    public String getAssetID(){return assetID;}
    public String getAssetPath(){return assetPath;}
}
