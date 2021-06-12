package com.opencart.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.utils.Constants;
import com.opencart.utils.ElementUtil;

import io.qameta.allure.Step;
public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//define constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);		
	}
	//create private By locators
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By forgotPassword = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By registerLink = By.linkText("Register");
	private By loginError = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//create page actions (methods)
	@Step("this is get title step")
	public String getLoginPageTitle() {
		return eleUtil.getTitle(5, Constants.LOGIN_PAGE_TITLE);
	}
	@Step("this is forgot pwd link step")
	public boolean isForgotPwdExist() {
		return eleUtil.isDisplayed(forgotPassword);
	}

	public AccountsPage doLogin(String user, String pwd) {
		eleUtil.doSendKeys(username, user);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	public boolean doLoginError(String user, String pwd) {
		eleUtil.doSendKeys(username, user);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return eleUtil.isDisplayed(loginError);
	} 
	
	public RegistrationPage registerUser() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
