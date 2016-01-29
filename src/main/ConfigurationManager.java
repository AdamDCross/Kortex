package main;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// XML file order:
@XmlType(propOrder = { "gameName", "screenResX", "screenResY", "gameDifficulty" })

@XmlRootElement
public class ConfigurationManager {
	
	/*
	 * Usage Example(s):
	 * ConfigurationManager.getInstance().setGameDifficulty(2)
	 * ConfigurationManager.save()
	 * ConfigurationManager.getInstance().getGameDifficulty();
	 */

	private static ConfigurationManager configManager = null;

	// Default save values for the XML file:
	private String gameName = "Project Abe is the best 5000";
	private int screenResX = 1024;
	private int screenResY = 720;
	private int gameDifficulty = 1;

	public static ConfigurationManager getInstance() {
		if (configManager == null) {
			configManager = new ConfigurationManager();
		}
		update();
		return configManager;
	}

	private static void update() {
		File configFile = new File("Configuration.xml");

		if (configFile.exists()) {
			System.out.println("Found existing configuration file..");
			/* Read in existing XML file for configuration */
			try {
				JAXBContext classContext = JAXBContext.newInstance(ConfigurationManager.class);
				Unmarshaller classUnmarshall = classContext.createUnmarshaller();
				configManager = (ConfigurationManager) classUnmarshall.unmarshal(configFile);
				System.out.println(configManager);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No Configuration file found..");
			try {
				/* Create new XML File and save default values */
				configFile.createNewFile();
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void save() {
		try {
			JAXBContext classContext = JAXBContext.newInstance(ConfigurationManager.class);
			Marshaller classMarshall = classContext.createMarshaller();
			classMarshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			classMarshall.marshal(configManager, new File("Configuration.xml"));
			classMarshall.marshal(configManager, System.out);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	@XmlElement(name = "screenResX")
	public void setScreenResX(int screenResX) {
		this.screenResX = screenResX;
	}

	@XmlElement(name = "screenResY")
	public void setScreenResY(int screenResY) {
		this.screenResY = screenResY;
	}

	@XmlElement(name = "gameDifficulty")
	public void setGameDifficulty(int gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}
}
