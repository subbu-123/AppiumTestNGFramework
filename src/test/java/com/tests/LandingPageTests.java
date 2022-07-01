package com.tests;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.Base;
import com.codoid.products.exception.FilloException;
import com.pages.LandingPage;
import com.utilities.DriverFactory;
import com.utilities.utilityClass;

public class LandingPageTests extends Base{

	LandingPage lp;
	utilityClass uc;
	
	@Test(priority=1, dataProvider="testData")
	public void ValidateUserFillsLandingPageForm(String name, String country) {

		lp = basepage.getInstance(LandingPage.class);
		lp.enterDataIntoFieldsOfLandingPage(name,country);
	}

	
	@Test(priority=2, dataProvider="testData")
	public void ValidateErrorMsgIfNameFieldIsNotEntered(String name, String country)
	{
		lp = basepage.getInstance(LandingPage.class);
		String errorMsg = lp.enterDataIntoFieldsOfLandingPage(country);
		Assert.assertEquals(errorMsg, "Please enter your name");
		
	}
	
	@DataProvider(name="testData")
	public Iterator<Object[]> getdata() throws FilloException {
		List<Object[]> data = uc.getDataFromExcel("./Resources/TestData.xlsx", "LandingPage");
		return data.iterator();
	}
}
