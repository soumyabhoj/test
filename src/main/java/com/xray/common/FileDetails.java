package com.xray.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

 

public class FileDetails {

	static HashMap<String, String> configProperties = null;
	static Properties propInfo = new Properties();
	static InputStream fileStream = null;
	static String filename = "config.properties";

	/**
	 * Gets a property value based on a given key value
	 *
	 * @param inputKey
	 *            type String
	 * @return String
	 */
	public static String getPropertyValueByKey(String inputKey) {

		String value = null;
		try {
			if (!inputKey.equals(null) && !inputKey.equals("")) {
				fileStream = new Object() {
				}.getClass().getClassLoader().getResourceAsStream(filename);
				if (fileStream == null) {
					throw new FileNotFoundException("Unable to find configuration file: " + filename);
				}
				propInfo.load(fileStream);
				Enumeration<?> e = propInfo.propertyNames();
				if (!e.equals(null)) {
					while (e.hasMoreElements()) {
						String keyName = (String) e.nextElement();
						if (inputKey.equals(keyName)) {
							value = propInfo.getProperty(keyName);
							break;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}