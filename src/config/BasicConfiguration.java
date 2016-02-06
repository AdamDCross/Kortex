package config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "gameName", "screenResX", "screenResY", "gameDifficulty" })

@XmlRootElement(name = "SettingsConfiguration")
public class BasicConfiguration {

	private static BasicConfiguration settingsConfig = null;

	private String gameName = "Kortex";
	private int screenResX = 640;
	private int screenResY = 480;
	private int gameDifficulty = 1;

	public static BasicConfiguration getInstance() {
		if (settingsConfig == null) {
			settingsConfig = new BasicConfiguration();
		}
		return settingsConfig;
	}

	public String getGameName() {
		return this.gameName;
	}

	public int getScreenResX() {
		return this.screenResX;
	}

	public int getScreenResY() {
		return this.screenResY;
	}

	public int getGameDifficulty() {
		return this.gameDifficulty;
	}

	@XmlElement(name = "gameName")
	public void setGameName(final String gameName) {
		this.gameName = gameName;
	}

	@XmlElement(name = "screenResX")
	public void setScreenResX(final int screenResX) {
		this.screenResX = screenResX;
	}

	@XmlElement(name = "screenResY")
	public void setScreenResY(final int screenResY) {
		this.screenResY = screenResY;
	}

	@XmlElement(name = "gameDifficulty")
	public void setGameDifficulty(final int gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}
}
