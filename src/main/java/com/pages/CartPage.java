package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;
import com.utilities.utilityClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends BasePage {
	
	WebDriverWait wait;

	public CartPage(AndroidDriver<AndroidElement> driver) {

		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(this.driver, 20);
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private AndroidElement cartIcon;
	
	@AndroidFindAll({@AndroidBy(id = "com.androidsample.generalstore:id/productName")})
	private List<AndroidElement> cartProducts;
	
	@AndroidFindAll({@AndroidBy(id = "com.androidsample.generalstore:id/productPrice")})
	private List<AndroidElement> cartProductPrices;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private AndroidElement cartTotalAmount;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	private AndroidElement checkboxElement;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private AndroidElement termsElement;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"CLOSE\")")
	private AndroidElement closeButton;
	
	@AndroidFindBy(className =  "android.widget.Button")
	private AndroidElement completePurchaseButton;
	
	
	public boolean IsUserOnCartPage()
	{
		wait.until(ExpectedConditions.visibilityOf(cartIcon));
		return cartIcon.isDisplayed();
		
	}
	
	public boolean checkProductPresentOnCartPage(String product)
	{
		wait.until(ExpectedConditions.visibilityOf(cartIcon));
		boolean flag= false;
		for (AndroidElement el: cartProducts) {
			if(el.getText().equalsIgnoreCase(product))
			{
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public double getSumOfProductPricesInCart() {
		
		double actualTotalAmount=0;
		for (AndroidElement price: cartProductPrices) {
			
			actualTotalAmount+= Double.parseDouble(price.getText().substring(1));
		}
		
		return actualTotalAmount;
	}
	
	public double getTotalAmountDisplayedOnCartPage()
	{
		String amount = cartTotalAmount.getText();
		double expectedTotalAmount = Double.parseDouble(amount.substring(1));
		return expectedTotalAmount;
	}

	public WebGoogleHomePage fillDetailsInCartPage()
	{
		utilityClass utils = new utilityClass();
		utils.tapByElement(driver, checkboxElement);
		utils.longPressByElement(driver, termsElement, 2);
		utils.tapByElement(driver, closeButton);
		utils.tapByElement(driver, completePurchaseButton);
		return getInstance(WebGoogleHomePage.class);
	}
	
}
