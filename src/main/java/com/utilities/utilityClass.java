package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.Base;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class utilityClass extends Base{

	
	public static List<Object[]> getDataFromExcel(String excelPath, String sheetname) throws FilloException
	{
		Fillo flo = new Fillo();
		Connection con = flo.getConnection(excelPath); // This connection class is of fillo not java
		String query = "Select * From "+ sheetname;
		Recordset rs = con.executeQuery(query);
		List<Object[]> testdata = new ArrayList<Object[]>();
		
		List<String> fieldNames = rs.getFieldNames();
		while(rs.next())
		{
			Object[] obj = new Object[fieldNames.size()];
			for(int i=0;i<fieldNames.size(); i++)
			{
				obj[i] = rs.getField(fieldNames.get(i));
			}
			testdata.add(obj);
		}

		rs.close();
		con.close();
		return testdata;
	}
	
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("d_M_YYYY_hh_mm_ss");
		String timestamp = sdf.format(new Date());
		File f1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagePath = "./Artifacts/Screenshots/"+screenshotName+"_"+timestamp+".png";
		File s = new File(imagePath);
		FileUtils.copyFile(f1, s);
	
		return imagePath;

	}
	
	public static String getScreenshotAsBase64(WebDriver driver)
	{
		
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);

	}
	
	public static void highLighterMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	public void tapByElement(AndroidDriver driver,AndroidElement el) {
		TouchAction action = new TouchAction(driver);
		action.tap(new TapOptions().withElement(ElementOption.element(el))).perform();
	}

	public void longPressByElement(AndroidDriver driver,AndroidElement el, int waitDuration) {
		TouchAction action = new TouchAction(driver);
		action.longPress(new LongPressOptions().withElement(ElementOption.element(el))
				.withDuration(Duration.ofSeconds(waitDuration))).release().perform();
	}
}
