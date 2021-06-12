package com.opencart.tests;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.utils.Constants;
import com.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void registerSetUp() {
		regisPage = lp.registerUser();
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		Object[][] data = ExcelUtil.getTestData(Constants.SHEET_NAME);
		return data;
	}
	
	public String generateRandomEmail() {
		Random randomGen = new Random();
		int randomNumber = randomGen.nextInt(1000);
		String email = "newtestautomation" + randomNumber + "@gmail.com";
		return email;
	}
	
	@Test(dataProvider = "getRegisterData")
	public void registerUserTest(String firstName, String lastName,	String telephone, String password, String subscribe) {
		
		String successMsg = regisPage.registeruser(firstName, lastName, generateRandomEmail(), telephone, password, subscribe);
		Assert.assertEquals(successMsg, "Your Account Has Been Created!");
	}
}
