package com.opencart.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.utils.ElementUtil;

public class searchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchResults = By.cssSelector("div.product-thumb");
	private By resultItems = By.cssSelector("div.product-thumb h4 a");
		
	public searchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getSearchResultsCount() {
		return eleUtil.getElements(5, searchResults).size();
	}
	
	public ProductPage selectProduct(String prodName) {
		List<WebElement> prodResultsList = eleUtil.getElements(5, resultItems);
		for(WebElement e: prodResultsList) {
			if(e.getText().equalsIgnoreCase(prodName)) {
				eleUtil.doClick(resultItems);
				break;
			}
		}
		return new ProductPage(driver);
	}
}
