package com.opencart.pages;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.opencart.utils.ElementUtil;
public class ProductPage {
	ElementUtil eleUtil;
	private By prodHeader = By.cssSelector("div#content h1");
	private By prodImage = By.cssSelector("ul.thumbnails a");
	private By prodInfo = By.xpath("(//div[@id='content']//ul[@class='list-unstyled']) [1] /li");
	private By prodPrice = By.xpath("(//div[@id='content']//ul[@class='list-unstyled']) [2] /li");
	private By quantity = By.cssSelector("div#product input#input-quantity");
	private By addToCart = By.cssSelector("div#product button#button-cart");
	private By successBanner = By.cssSelector("div.alert.alert-success.alert-dismissible");
	
	public ProductPage(WebDriver driver) {
		eleUtil = new ElementUtil(driver);
	}
	public String getProdHeader() {
		return eleUtil.getText(5, prodHeader);
	}
	public int getImageCount() {
		return eleUtil.getElements(5, prodImage).size();
	}
	public Map<String, String> getProductInfo() {
		Map<String, String> prodDetails = new HashMap<String, String>();
		prodDetails.put("Name", getProdHeader());
		
		List<WebElement> prodInfoList = eleUtil.getElements(5, prodInfo);
		for(WebElement e:prodInfoList) {
			String [] metaData = e.getText().split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			prodDetails.put(metaKey, metaValue);
		}
		List<WebElement> priceList = eleUtil.getElements(5, prodPrice);
		String price = priceList.get(0).getText().trim();
		String taxPrice = priceList.get(1).getText().trim();
			prodDetails.put("price", price);
			prodDetails.put("taxPrice", taxPrice);
			
			return prodDetails;		
	}
	public void selectQuantity(String qty) {
		eleUtil.doSendKeys(quantity, qty);
	}
	public void addToCart() {
		eleUtil.doClick(addToCart);
	}
	public String getSuccessMessage() {
		return eleUtil.getText(10, successBanner);
	}
}
