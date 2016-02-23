package config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;

/**
 * Created by brownt on 12/02/2016.
 */

//XML file order:
@XmlType(propOrder = {"unlockName", "unlockXPReq", "unlockScrapReq", "unlockIcon"})

@XmlRootElement(name = "BasicScrapConfiguration")

public class BasicScrapConfiguration {

    private static BasicScrapConfiguration scrapConfig = null;

    private String unlockName = "";
    private int unlockXPReq = 0;
    private int unlockScrapReq = 0;
    private File unlockIcon;

    public static BasicScrapConfiguration getInstance() {
        if (scrapConfig == null) {
            scrapConfig = new BasicScrapConfiguration();
        }
        return scrapConfig;
    }

    public String getUnlockName() {
        return unlockName;
    }
    @XmlElement(name = "unlockName")
    public void setUnlockName(String unlockName) {
        this.unlockName = unlockName;
    }

    public int getUnlockXPReq() {
        return unlockXPReq;
    }
    @XmlElement(name = "unlockXPReq")
    public void setUnlockXPReq(int unlockXPReq) {
        this.unlockXPReq = unlockXPReq;
    }

    public int getUnlockScrapReq() {
        return unlockScrapReq;
    }
    @XmlElement(name = "unlockScrapReq")
    public void setUnlockScrapReq(int unlockScrapReq) {
        this.unlockScrapReq = unlockScrapReq;
    }

    public File getUnlockIcon() {
        return unlockIcon;
    }
    @XmlElement(name = "unlockIcon")
    public void setUnlockIcon(File unlockIcon) {
        this.unlockIcon = unlockIcon;
    }
}
