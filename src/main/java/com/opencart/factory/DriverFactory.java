package com.opencart.factory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverFactory {
	//WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionsManager opsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public WebDriver init_driver(Properties prop) {
		opsManager = new OptionsManager(prop);
		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(opsManager.setChromeOptions()));
			//driver = new ChromeDriver(opsManager.setChromeOptions());
			break;
			
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(opsManager.setFirefoxOptions()));
			//driver = new FirefoxDriver(opsManager.setFirefoxOptions());
			break;
			
		case "safari":
			tlDriver.set(new SafariDriver());
			//driver = new SafariDriver();
			break;

		default:
			System.out.println("The driver provided is not supported by the framework");
			break;
		}		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();		
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties init_properties() {
		FileInputStream ip = null;
		prop = new Properties();
		String envVariable = System.getProperty("env");
		
		if(envVariable == null) {
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
			switch (envVariable) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
				
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
				
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			default:
				break;
			}
		}
			catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
		try {
			prop.load(ip);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		}	
	
	public String getScreenShotPath() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE); //this gets the ss in a temporary file
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png"; //this is just a path
		File destination = new File(path); //here we have created a new file at destination path
		try {
			FileUtils.copyFile(src, destination); //copy the src file to destination path
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path; //this will return the path of the ss.
	}
}


