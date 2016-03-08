package config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.Log;
import static executionEngine.DriverScript.OR;

public class ActionKeywords {

	public static WebDriver driver;
	
	public void openBrowser(String object){
		Log.info("Opening Browser");
		driver= new FirefoxDriver();
	}
	
	public static void navigate(String object){
		Log.info("Navigating to URL");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.URL);
	}
	
	public static void waitFor(String object) throws Exception{		
		Thread.sleep(15000);
	}
	
	public static void input_Username(String object){
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.UserName);
	}

	public static void input_Password(String object){
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.Password);
	}
	
	public static void click_Login(String object){
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}
	
	public static void click_Project(String object){
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}
	
	public static void click_Usericon(String object){
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}
	
	public static void click_Signout(String object){
		driver.findElement(By.xpath(OR.getProperty(object))).click();
	}
	
	public static void closeBrowser(String object){
		driver.quit();
	}
}
