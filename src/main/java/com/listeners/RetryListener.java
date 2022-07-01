package com.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

	/* This is implemented so that user doesn't have to attach retry mechanism with each @Test annotation rather providing this class in 
	listeners tag within testng xml will automatically hear out the failed test cases during our execution and re execute them */
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		annotation.setRetryAnalyzer(Retry.class);
		
	}

	
}
