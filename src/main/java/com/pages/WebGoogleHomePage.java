package com.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WebGoogleHomePage extends BasePage {
	
	WebDriverWait wait;

	public WebGoogleHomePage(AndroidDriver<AndroidElement> driver) {

		super(driver);
		Set<String> handles = driver.getContextHandles();
		List<String> ctxhandles = new ArrayList(handles);
		for(String handle : ctxhandles)
		{
			if(handle.contains("WEB"))
			{
				driver.context(handle);
				break;
			}
		}
		
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(this.driver, 20);
	}
	
	@FindBy(xpath = "//*[@alt='Google']")
	WebElement googleIcon;
	

	public boolean IsUserOnWebGoogleHomePage()
	{
		wait.until(ExpectedConditions.visibilityOf(googleIcon));
		return googleIcon.isDisplayed();
		
	}
}
