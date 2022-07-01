package com.utilities;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	
	public static ThreadLocal<AndroidDriver<AndroidElement>> tldriver = new ThreadLocal<AndroidDriver<AndroidElement>>();
	
	public static AndroidDriver<AndroidElement> initializeDriver(String appName, String device, URL url)
	{
		File f = new File(System.getProperty("user.dir")+ "/src/test/resources/Apps/"+ appName +".apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.APP, f.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		//cap.setCapability("autolaunch", false);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		
		tldriver.set(new AndroidDriver<AndroidElement>(url,cap));
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return getDriver();
		
	}
	
	public static AndroidDriver<AndroidElement> getDriver()
	{
		return tldriver.get();
	}

}
