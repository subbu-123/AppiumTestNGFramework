package com.tests;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.Base;
import com.codoid.products.exception.FilloException;
import com.pages.CartPage;
import com.pages.LandingPage;
import com.pages.ProductsPage;
import com.pages.WebGoogleHomePage;
import com.utilities.utilityClass;

public class CartPageTests extends Base{
	
	LandingPage lp;
	ProductsPage pp;
	CartPage cp;
	WebGoogleHomePage wghp;
	utilityClass uc;
	
	@Test(priority=1, dataProvider="CartPageTestData")
	public void validateSelectedProductsAppearOnCartPage(String name,String country, String product1, String product2)
	{
		lp = basepage.getInstance(LandingPage.class);
		pp = lp.enterDataIntoFieldsOfLandingPage(name, country);
		String[] productNames= {product1, product2};
		pp.addProductsToCart(productNames);
		cp= pp.navigateToCartPage();
		for (String product : productNames)
		{
			boolean productFlag = cp.checkProductPresentOnCartPage(product);
			Assert.assertTrue(productFlag, product+" is not appearing on cart page");
		}
		
	}
	
	@Test(priority=2, dataProvider="CartPageTestData")
	public void validateSumOfProductPricesEqualsToTotalAmountOnCartPage(String name,String country, String product1, String product2)
	{
		lp = basepage.getInstance(LandingPage.class);
		pp = lp.enterDataIntoFieldsOfLandingPage(name, country);
		String[] productNames= {product1, product2};
		pp.addProductsToCart(productNames);
		cp= pp.navigateToCartPage();
		double summedPrice = cp.getSumOfProductPricesInCart();
		double totalAmount = cp.getTotalAmountDisplayedOnCartPage();
		Assert.assertEquals(summedPrice, totalAmount);
	}
	
	@Test(priority=3, dataProvider="CartPageTestData")
	public void validateUserPerformsActionsInCartPageAndNavigatesToWebHomePage(String name,String country, String product1, String product2)
	{
		lp = basepage.getInstance(LandingPage.class);
		pp = lp.enterDataIntoFieldsOfLandingPage(name, country);
		String[] productNames= {product1, product2};
		pp.addProductsToCart(productNames);
		cp= pp.navigateToCartPage();
		wghp = cp.fillDetailsInCartPage();
		Assert.assertTrue(wghp.IsUserOnWebGoogleHomePage(),"User is not navigated to google web home page");
	}
	
	@DataProvider(name="CartPageTestData")
	public Iterator<Object[]> getData() throws FilloException {
		List<Object[]> data = uc.getDataFromExcel("./Resources/TestData.xlsx", "CartPage");
		return data.iterator();
	} 

}
