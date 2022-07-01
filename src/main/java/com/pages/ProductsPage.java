package com.pages;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BasePage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage extends BasePage {

	WebDriverWait wait;

	public ProductsPage(AndroidDriver<AndroidElement> driver) {

		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(this.driver, 20);
	}
	
	@AndroidFindAll({@AndroidBy(id = "com.androidsample.generalstore:id/productName")})
	private List<AndroidElement> displayedProducts;
	
	@AndroidFindAll({@AndroidBy(uiAutomator = "new UiSelector().textContains(\"ADD TO CART\")")})
	private List<AndroidElement> displayedAddToCartButtons;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private AndroidElement cartButton;
	
	
	public void addProductsToCart(String[] productNames)
	{
		for (String name : productNames) {
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().text(\""+ name +"\"))");

			int count = 0;
			for (AndroidElement el : displayedProducts) {
				if (el.getText().equalsIgnoreCase(name)) {
					displayedAddToCartButtons.get(count).click();
					break;
				}
				count++;
			}
		}
	}
	
	
	public CartPage navigateToCartPage()
	{
		cartButton.click();
		return getInstance(CartPage.class);
	}
	
	
	
	
}
