package com.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.opencart.base.BaseTest;
import com.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("NYAAR-1500: This is sample epic")
@Story("NYAAR-13200: This is a sample story")

public class LoginPageTest extends BaseTest{
	
	@Description("this is a login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test (priority = 1)
	public void loginPageTitleTest() {
		String title = lp.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void validateForgotPwdTest() {
		Assert.assertTrue(lp.isForgotPwdExist());
	}

	@Test (priority = 3)
	public void loginTest() {
		lp.doLogin(prop.getProperty("userId"), prop.getProperty("pwd"));
	}
	
	@DataProvider
	public Object [][] getNegativeLoginData() {
		Object [][] data = {{"test@44", "test34"}, {"test67@gmail.com", "tes67"}, {" ", " "}};
		return data;
	}
	
	@Test(dataProvider = "getNegativeLoginData")
	public void negativeLoginTest(String un, String pwd) {
		boolean status = lp.doLoginError(un, pwd);
		Assert.assertTrue(status);
	}
}
