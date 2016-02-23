package config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//XML file order:
@XmlType(propOrder = { "author", "age" })

@XmlRootElement(name = "BasicTemplate")
public class BasicTemplate {

	/*
	 * This is a Template Object, shows clearly how to make a new configuration
	 * file for the ConfigurationManager to handle!
	 */

	private static BasicTemplate templateConfig = null;
	
	// Default save values for the XML file:
	private String author = "Tom";
	private int age = 20;

	public static BasicTemplate getInstance() {
		if (templateConfig == null) {
			templateConfig = new BasicTemplate();
		}
		return templateConfig;
	}

	public String getAuthor() {
		return author;
	}

	public int getAge() {
		return age;
	}

	@XmlElement(name = "author")
	public void setAuthor(final String author) {
		this.author = author;
	}

	@XmlElement(name = "age")
	public void setAge(final int age) {
		this.age = age;
	}
}
