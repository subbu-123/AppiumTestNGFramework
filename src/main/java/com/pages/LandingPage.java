package com.pages;


import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LandingPage extends BasePage {
	
	//AndroidDriver<AndroidElement> driver;
	WebDriverWait wait;
	
	public LandingPage(AndroidDriver<AndroidElement> driver) {
		
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(this.driver, 20);
	}


	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Enter name here\")")
	private AndroidElement nameField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Female\")")
	private AndroidElement femaleRadioBtn;
	
	@AndroidFindBy(id = "android:id/text1")
	private AndroidElement ListBtn;
	
	@AndroidFindBy(className = "android.widget.Button")
	private AndroidElement LetsShopBtn;
	
	@AndroidFindBy(xpath = "//android.widget.Toast[1]")
	private AndroidElement errorToastMsg;
	
	public ProductsPage enterDataIntoFieldsOfLandingPage(String name, String country)
	{
		nameField.click();
		nameField.sendKeys(name);
		driver.hideKeyboard();
		femaleRadioBtn.click();
		ListBtn.click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + country + "\"))").click();
		LetsShopBtn.click();
		return getInstance(ProductsPage.class);
	}
	
	public String enterDataIntoFieldsOfLandingPage(String country)
	{
		femaleRadioBtn.click();
		ListBtn.click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\"" + country + "\"))").click();
		LetsShopBtn.click();
		String errorMsg = errorToastMsg.getText();
		return errorMsg;
	}

}
