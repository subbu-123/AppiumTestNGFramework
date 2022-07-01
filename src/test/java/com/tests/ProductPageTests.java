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
import com.utilities.utilityClass;

public class ProductPageTests extends Base{

	LandingPage lp;
	ProductsPage pp;
	CartPage cp;
	utilityClass uc;
	
	@Test(priority=1, dataProvider="ProductsPageTestData")
	public void validateProductsAreAddedToCart(String name,String country, String product1, String product2)
	{
		lp = basepage.getInstance(LandingPage.class);
		pp = lp.enterDataIntoFieldsOfLandingPage(name, country);
		String[] productNames= {product1, product2};
		pp.addProductsToCart(productNames);
		
	}
	

	@Test(priority=2, dataProvider="ProductsPageTestData")
	public void validateUserNavigatesToCartPageSuccessfully(String name,String country, String product1, String product2)
	{
		lp = basepage.getInstance(LandingPage.class);
		pp = lp.enterDataIntoFieldsOfLandingPage(name, country);
		String[] productNames= {product1, product2};
		pp.addProductsToCart(productNames);
		cp= pp.navigateToCartPage();
		Assert.assertTrue(cp.IsUserOnCartPage());
	}
	
	@DataProvider(name="ProductsPageTestData")
	public Iterator<Object[]> getData() throws FilloException {
		List<Object[]> data = uc.getDataFromExcel("./Resources/TestData.xlsx", "ProductsPage");
		return data.iterator();
	} 
}
