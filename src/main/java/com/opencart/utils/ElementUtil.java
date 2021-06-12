package com.opencart.utils;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencart.factory.DriverFactory;
public class ElementUtil {

private JavaScriptUtil jsUtil;
private WebDriver driver;
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public String getTitle(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}
	
	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;
	}
	
	public List<WebElement> getElements(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public String getText(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return ele.getText();
	}
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);	
	}
	
	public void doClick(By locator) {
		getElement(locator).click();	
	}
	
	public boolean isDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List <String> getElementsTextList(By locator) {
		List <String> textList = new ArrayList<String>();
		List<WebElement> elementsList = getElements(5, locator);
		
		for(WebElement e : elementsList) {
			if(!e.getText().isEmpty()) {
				textList.add(e.getText());
			}	
		}
		return textList;
	}
	
	public void doSelectDropdownByIndex(By locator, int index) {
		Select sel_obj = new Select(getElement(locator));
		sel_obj.selectByIndex(index);	
	}
	
	public void doSelectDropdownByVisText(By locator, String visText) {
		Select sel_obj = new Select(getElement(locator));
		sel_obj.selectByVisibleText(visText);	
	}
	
	public void doSelectDropdownByValue(By locator, String value) {
		Select sel_obj = new Select(getElement(locator));
		sel_obj.selectByValue(value);	
	}
	
//this method will select the dropdown values without the use of above 3 methods
	public void doSelectDropdownValue(By locator, String value) {
		Select sel_obj = new Select(getElement(locator));
		List <WebElement> optionsList = sel_obj.getOptions();
		
		for(WebElement e : optionsList) {
			if(e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}
	
//this method will select the dropdown value without select class methods
	public void doSelectWithOutSelectClass(By locator, String value) {
		List <WebElement> list = getElements(5, locator);
		
		for(WebElement e : list) {
			if(e.getText().equals(value)) {
				e.click();
			}
		}
	}
	
	public void doSelectFromJQueryDropdown(By locator, String... options) {
		List <WebElement> eleList = getElements(5, locator);
		
		if(!options[0].equalsIgnoreCase("All")) {
			for(int i = 0; i<=eleList.size(); i++) {
				String text = eleList.get(i).getText();
				
				for(int j = 0; j<options.length; j++) {
					if(text.equals(options[j])) {
						eleList.get(i).click();
						break;
					}
				}
			}
		}
		
		else {
		try {
			for(int i = 0; i<eleList.size(); i++) {
				eleList.get(i).click();
			}
		}
		catch(Exception e) {
			
		}
	}	
}
	public void doDeSelectInJQyeryDropdown(By locator, String... options) {
		List <WebElement> eleList = getElements(5, locator);
		
		if(!options[0].equalsIgnoreCase("All")) {
			for(WebElement e : eleList) {
				String text = e.getText();
				
				for(int j = 0; j<options.length; j++) {
					if(text.equals(options[j])) {
							e.click();
							break;			
					}
				}
			}
		}
		else {
			for(WebElement e : eleList) {
					e.click();
			}	
		}
		
	}
	
	public void doSelectFromSearchBar(By mainLocator, By locator, 
			String enteredValue, String expValue) throws InterruptedException {
		
		doSendKeys(mainLocator, enteredValue);
		Thread.sleep(5000);
		List<WebElement> eleList = getElements(5, locator);

		for(WebElement e : eleList) {
			if(e.getText().equals(expValue)) {
				e.click();
			}
		}
	}
}
