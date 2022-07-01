package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {
	
	
	public static String getPropertyValue(String propertyName)
	{
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/config.properties");
			prop.load(fis);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop.getProperty(propertyName);
	}

}
