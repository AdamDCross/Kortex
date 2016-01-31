package config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//XML file order:
@XmlType(propOrder = { "playerName", "playerScore", "levelsUnlocked" })

@XmlRootElement(name = "BasicGameSave")
public class BasicGameSave {

	private static BasicGameSave gameSaveConfig = null;

	private String playerName = "";
	private int playerScore = 0;
	private int levelsUnlocked = 0;

	public static BasicGameSave getInstance() {
		if (gameSaveConfig == null) {
			gameSaveConfig = new BasicGameSave();
		}
		return gameSaveConfig;
	}

	@XmlElement(name = "playerName")
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(final String playerName) {
		this.playerName = playerName;
	}

	@XmlElement(name = "playerScore")
	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(final int playerScore) {
		this.playerScore = playerScore;
	}

	@XmlElement(name = "levelsUnlocked")
	public int getLevelsUnlocked() {
		return levelsUnlocked;
	}

	public void setLevelsUnlocked(final int levelsUnlocked) {
		this.levelsUnlocked = levelsUnlocked;
	}

}