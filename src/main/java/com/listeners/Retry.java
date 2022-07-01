package com.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	//  This is implemented to re run any failed test cases during our execution "maxRetryCount" times before declaring it as failed
	private int retryCount = 0;
	  private static final int maxRetryCount = 2;
	 
	  @Override
	  public boolean retry(ITestResult result) {
	    if (retryCount < maxRetryCount) {
	      retryCount++;
	      return true;
	    }
	    return false;
	  }


	
}
