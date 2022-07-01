package com.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static SimpleDateFormat sdf = new SimpleDateFormat("d_M_YYYY_hh_mm_ss");
	private static String timestamp = sdf.format(new Date());
    private static String reportFileName = "ExtentReport_"+timestamp+".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "ExtentReports";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
  
 
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance() {
        //String fileName = getReportPath(reportFilepath);
        
        ExtentSparkReporter spark = new ExtentSparkReporter(reportFileLocation);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Extent Reporting");
        spark.config().setReportName("General Store Mobile Application Automation Suite");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
 
        extent = new ExtentReports();
        extent.attachReporter(spark);
        //Set environment details
        extent.setSystemInfo("Tester", "Subhankar");
        extent.setSystemInfo("Company", "TCS");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");
 
        return extent;
    }
     
    //Create the report path
    private static String getReportPath (String path) {
    	File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                
                return reportFileLocation;
            } else {
                
                return System.getProperty("user.dir");
            }
        } 
		return reportFileLocation;
    }
 
}
