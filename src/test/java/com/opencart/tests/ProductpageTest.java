package com.opencart.tests;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.opencart.base.BaseTest;
import com.opencart.utils.Constants;
public class ProductpageTest extends BaseTest{
	SoftAssert softAssert = new SoftAssert();
	@BeforeClass
	public void prodPageSetUp() {
		accPage = lp.doLogin(prop.getProperty("userId"), prop.getProperty("pwd"));
	}
	
	@DataProvider
	public Object [][] searchProdData() {
		Object [] [] data = {{"iMac"}, {"MacBook"}, {"iPhone"}};
		return data;
	}
	
	@Test(dataProvider = "searchProdData")
	public void productCountTest(String searchData) {
		searchResPage = accPage.searchProduct(searchData);
		int productCount = searchResPage.getSearchResultsCount();
		Assert.assertTrue(productCount >= 1);
	}
	@Test
	public void productHeaderTest() {
		searchResPage = accPage.searchProduct("iMac");
		productPage = searchResPage.selectProduct("imac");
		String prodHeader = productPage.getProdHeader();
		Assert.assertEquals(prodHeader, "iMac");
	}
	@Test
	public void imageCountTest() {
		searchResPage = accPage.searchProduct("iMac");
		productPage = searchResPage.selectProduct("imac");
		int imageCount = productPage.getImageCount();
		Assert.assertTrue(imageCount == 3);
	}
	@Test
	public void productDetailsTest() {
		searchResPage = accPage.searchProduct("iMac");
		productPage = searchResPage.selectProduct("imac");
		Map<String, String> prodInfoDetails = productPage.getProductInfo();
		String name = prodInfoDetails.get("Name");
		String brand = prodInfoDetails.get("Brand");
		String price = prodInfoDetails.get("price");
		String taxPrice = prodInfoDetails.get("taxPrice");
		softAssert.assertEquals(name, "iMac");
		softAssert.assertEquals(brand, "Apple");
		softAssert.assertEquals(price, "$100.00");
		softAssert.assertEquals(taxPrice, "Ex Tax: $100.00");
		softAssert.assertAll();
	}
	@Test
	public void buyProduct() {
		searchResPage = accPage.searchProduct("iMac");
		productPage = searchResPage.selectProduct("imac");
		productPage.selectQuantity("1");
		productPage.addToCart();
		String successMessage = productPage.getSuccessMessage();
		Assert.assertTrue(successMessage.contains(Constants.PROD_SUCCESS_MSG));
	}
}
