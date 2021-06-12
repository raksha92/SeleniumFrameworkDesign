package com.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	private WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String title = js.executeScript("return document.title;").toString();
		return title;
	}
	
	public void refreshPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}
	
	public void generateAlert(String alertMessage) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('"+alertMessage+"')");
	}
	
	public String getInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
	}
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 5; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}
	
	public void clickOnElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	public void scrollDownPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollUpPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	public void scrollUptoSomeRange(int range) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, "+range+")");
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void sendKeysUsingId(String id, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('"+id+"').value='"+text+"'");
	}
	
}
