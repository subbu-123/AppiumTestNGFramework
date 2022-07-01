package com.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.utilities.AppiumServer;
import com.utilities.ConfigReader;
import com.utilities.DriverFactory;
import com.utilities.EmulatorFactory;

public class Base {

	public AndroidDriver<AndroidElement> driver;
	public BasePage basepage;
	
	ConfigReader cr;
	AppiumServer as = new AppiumServer();

	@BeforeSuite
	public void startAppiumServer() {
		try {
			as.startServer();
			String emulatorFlag =  cr.getPropertyValue("IsEmulator");
			if(emulatorFlag.equalsIgnoreCase("true"))
			{
				EmulatorFactory.startEmulator();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void stopAppiumServer() {
		
		String emulatorFlag =  cr.getPropertyValue("IsEmulator");
		if(emulatorFlag.equalsIgnoreCase("true"))
		{
			EmulatorFactory.stopEmulator();
		}
		as.stopServer();
	}

	@BeforeMethod
	public void driverInitiation() {
		String appName = ConfigReader.getPropertyValue("AppName");
		String device = ConfigReader.getPropertyValue("DeviceName");
		URL url = as.getServerUrl();
		driver = DriverFactory.initializeDriver(appName, device, url);
		// driver.launchApp();
		basepage = new BasePage(driver);

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
