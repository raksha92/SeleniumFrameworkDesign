package com.opencart.pages;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.utils.Constants;
import com.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By accHeader = By.cssSelector("div#logo h1");
	private By accSections = By.cssSelector("div#content h2");
	private By logOutLink = By.linkText("Logout");
	private By searchBar = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccPageHeader() {
		return eleUtil.getText(5, accHeader);
	}
	
	public String getAccPageTitle() {
		return eleUtil.getTitle(5, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	public List<String> getSectionsTextList() {
		List<String> list = eleUtil.getElementsTextList(accSections);
		Collections.sort(list);
		return list;
	}
	
	public boolean isLogoutExist() {
		return eleUtil.isDisplayed(logOutLink);		
	}
	
	public searchResultsPage searchProduct(String prodName) {
		eleUtil.doSendKeys(searchBar, prodName);
		eleUtil.doClick(searchButton);
		return new searchResultsPage(driver);
	}
}
