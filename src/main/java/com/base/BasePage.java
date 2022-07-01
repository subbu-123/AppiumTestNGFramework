package com.base;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BasePage {
	
	public AndroidDriver<AndroidElement> driver;

	public BasePage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public <T extends BasePage> T getInstance(Class<T> pageclass)
	{
		try {
			
			return pageclass.getConstructor(AndroidDriver.class).newInstance(this.driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
}
