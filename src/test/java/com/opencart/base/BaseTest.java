package com.opencart.base;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.opencart.factory.DriverFactory;
import com.opencart.listeners.AllureReportListeners;
import com.opencart.pages.AccountsPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.ProductPage;
import com.opencart.pages.RegistrationPage;
import com.opencart.pages.searchResultsPage;

@Listeners(AllureReportListeners.class)
public class BaseTest {
	public WebDriver driver;
	DriverFactory df;
	public LoginPage lp;
	public Properties prop;
	public AccountsPage accPage;
	public searchResultsPage searchResPage;
	public ProductPage productPage;
	public RegistrationPage regisPage;
	
	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.init_properties();
		prop.setProperty("browser", browserName);
		driver = df.init_driver(prop);
		lp = new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
