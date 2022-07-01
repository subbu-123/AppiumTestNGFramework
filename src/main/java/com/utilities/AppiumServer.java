package com.utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {
	
	static AppiumServiceBuilder builder;
	static AppiumDriverLocalService service;
	
	public void startServer() throws IOException
	{
		builder = new AppiumServiceBuilder();
		builder.usingAnyFreePort();
		//builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
		File logFile = new File("./logs/AppiumServer.log");
		if(logFile.exists ())
		{
			Files.delete(logFile.toPath());
		}
		builder.withLogFile(logFile);
		
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		service.clearOutPutStreams();
	}
	
	public URL getServerUrl()
	{
		return service.getUrl();
	}
	
	public void stopServer()
	{
		service.stop();
	}

}
