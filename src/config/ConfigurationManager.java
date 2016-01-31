package config;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ConfigurationManager {

	// Add all configurations to this array:
	private final static Object[] configurationList = { BasicConfiguration.getInstance(), BasicTemplate.getInstance() };

	private static ConfigurationManager configManager = null;

	private static JAXBContext classContext;
	private static Unmarshaller classUnmarshall;
	private static Marshaller classMarshall;

	public static ConfigurationManager getInstance() {
		if (configManager == null) {
			configManager = new ConfigurationManager();
		}
		for (final Object configurationList : configurationList) {
			update(configurationList, new File(configurationList.getClass().getName()));
		}
		return configManager;
	}

	private static void update(Object target, final File configFile) {
		if (configFile.exists()) {
			try {
				classContext = JAXBContext.newInstance(target.getClass());
				classUnmarshall = classContext.createUnmarshaller();
				target = classUnmarshall.unmarshal(configFile);
			} catch (final JAXBException e) {
				e.printStackTrace();
			}
		} else {
			try {
				configFile.createNewFile();
				save(target, configFile);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void save(final Object target, final File configFile) {
		try {
			classContext = JAXBContext.newInstance(target.getClass());
			classMarshall = classContext.createMarshaller();
			classMarshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			classMarshall.marshal(target, configFile);
			classMarshall.marshal(target, System.out);
		} catch (final JAXBException e) {
			e.printStackTrace();
		}

	}

}
