package com.utilities;

import java.io.IOException;

public class EmulatorFactory {
	
	public static void startEmulator()
	{
		String filepath = System.getProperty("user.dir")+ "\\Resources\\StartEmulator.bat";
		try {
			Process batch = Runtime.getRuntime().exec("cmd.exe /c start "+filepath);
			batch.waitFor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void stopEmulator()
	{
		String filepath = System.getProperty("user.dir")+ "\\Resources\\StopEmulator.bat";
		try {
			Process batch = Runtime.getRuntime().exec(filepath);
			batch.waitFor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
