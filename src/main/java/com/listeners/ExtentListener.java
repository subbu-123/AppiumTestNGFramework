package com.listeners;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.utilities.DriverFactory;
import com.utilities.utilityClass;


public class ExtentListener implements ITestListener {

	private static Logger log = LogManager.getLogger(ExtentListener.class);
	private static ExtentReports extent = ExtentManager.getInstance();
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

		if (extent != null) {

			extent.flush();
			ExtentManager.getInstance().flush();
		}

	}

	public void onStart(ITestContext iTestContext) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		try {
			log.error("Test case: " + result.getMethod().getMethodName() + " is failed");
			log.error("Failure reason: " + result.getThrowable().toString());
			
			//String Path = utilityClass.getScreenshot(driver, result.getName());
			String base64Path= utilityClass.getScreenshotAsBase64(DriverFactory.getDriver());
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());

			testReport.get().log(Status.FAIL,
					"Test Case " + result.getMethod().getConstructorOrMethod().getName().toUpperCase() + " Failed");
		/*	testReport.get()
					.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
							+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
							+ "</details>" + " \n");  */
			
			testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
					+ "</font>" + "</b >" + "</summary>" + result.getThrowable().toString().replaceAll(",", "<br>") + "<br>" + exceptionMessage.replaceAll(",", "<br>")
					+ "</details>" + " \n");
			
			testReport.get().fail("Screenshot",MediaEntityBuilder.createScreenCaptureFromBase64String(base64Path).build());
			
			
		} catch (Exception e) {e.printStackTrace();}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		log.info("Test case: " + result.getMethod().getMethodName() + " is skipped");		
		
		testReport.get().log(Status.SKIP, "Test Case " +
		  result.getMethod().getConstructorOrMethod().getName().toUpperCase() +
		  " Skipped");
		 

	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		log.info("***************Test case: " + result.getMethod().getMethodName() + " has started*********************");
		
		ExtentTest test = extent.createTest(result.getTestClass().getName() +
		  "     @TestCase : " + result.getMethod().getMethodName());
		  testReport.set(test);
		 
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		log.info("Test case: " + result.getMethod().getMethodName() + " is passed");
		
		testReport.get().log(Status.PASS, "Test Case " +
		  result.getMethod().getConstructorOrMethod().getName().toUpperCase() +
		  " passed");
		 
		
	}

}
