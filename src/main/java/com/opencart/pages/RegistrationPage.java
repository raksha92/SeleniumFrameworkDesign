package com.opencart.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.opencart.utils.ElementUtil;
public class RegistrationPage {
	public ElementUtil eleUtil;
	
	public RegistrationPage(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
	}
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//input[@name='newsletter'])[1]");
	private By subscribeNo = By.xpath("(//input[@name='newsletter'])[2]");
	private By checkbox = By.name("agree");
	private By continueBtn = By.xpath("//input[@value='Continue']");
	private By logOut = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By successMsg = By.xpath("//div[@id='content']/h1");
	
	
	public String registeruser(String firstName, String lastName, 
								String email, String telephone, 
									String password, String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPwd, password);

		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(checkbox);
		eleUtil.doClick(continueBtn);
		String successMsg = eleUtil.getText(5, this.successMsg);
		eleUtil.doClick(logOut);
		eleUtil.doClick(registerLink);
		return successMsg;
	}
	
	
	
	
	
}
