/*
 * Utility functions - Commonly used functions in the test cases
 * 1. Function to generate random number
 * 2. Function to wait explicitly until page is loaded
 * 3. Function to select the web browser to run the test case
 * 4. Function to open the collaborator in the web browser
 * 5. Function to login to the collaborator
 * 6. Function to logout from the collaborator
 * 7. Function to close the web browser
 */
package utility;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class utilFunctions {
	public String _url = "https://dus-col-001.paris-ag.com/211122/login.html";
	//public String _url = "https://ber-col-001.prea.local/211122/login.html";
	
	//1. Function to generate random number
	public int generateRandomNumber() {
		  Random rand = new Random();
		  int randomNum = rand.nextInt(100000);
		  return randomNum;
    }
	
	//2. Function to wait explicitly until page is loaded
	public void waitForPageLoad(WebDriver driver) {
		  //explicit wait
		  WebDriverWait wait = new WebDriverWait(driver,60);
		  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"head_status\"]")));
    }
	
	//3. Function to select the web browser to run the test case
	public WebDriver selectBrowser(String browserType) {
		if(browserType.equals("Firefox")) {
			  //Set the system property to point to gecko driver
			  System.setProperty("webdriver.gecko.driver", "C:\\Users\\ishvarya\\Selenium\\SeleniumDrivers\\geckodriver-v0.20.1-win64\\geckodriver.exe");	
		  
			  //Create a new instance of the Firefox driver
			  WebDriver driver = new FirefoxDriver();
			  return driver;
		  }
		  else if(browserType.equals("Chrome")) {
			  //Set the system property to point to chrome driver
			  System.setProperty("webdriver.chrome.driver", "C:\\Users\\ishvarya\\Selenium\\SeleniumDrivers\\chromedriver_win32\\chromedriver.exe");
			  
			  //Create a new instance of the Chrome driver
			  WebDriver driver = new ChromeDriver();
			  return driver;
		  }
		  else if(browserType.equals("IExplorer")) {
			  //Set the system property to point to IE Driver
			  System.setProperty("webdriver.ie.driver", "C:\\Users\\ishvarya\\Selenium\\SeleniumDrivers\\IEDriverServer_Win32_3.9.0\\IEDriverServer.exe");
			  
			  //Create a new instance of the IExplorer driver
			  WebDriver driver = new InternetExplorerDriver();
			  return driver;
		  }
		return null;
	}
	
	//4. Function to open the collaborator in the web browser
	public void _openBrowser(WebDriver driver) {
		//Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	      
	      //Maximize the window
		  driver.manage().window().maximize();
		  
	      //Launch collaborator
	      driver.get(_url);
	      
	      Reporter.log("Browser opened successfully.");
	}
	
	//5. Function to login to the collaborator
	public void _logIn(String sUsername, String sPassword,WebDriver driver) {
		  //Enter the username
		  WebElement usernameText = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/table/tbody/tr[2]/td/input"));
		  usernameText.sendKeys(sUsername);
		  
		  //Enter the password
		  WebElement passwordText = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/table/tbody/tr[3]/td/input"));
		  passwordText.sendKeys(sPassword);
		  
		  //Click on login button
		  WebElement loginButton = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/table/tbody/tr[4]/td/input"));
		  loginButton.click();
		  
		  // Print a Log In message to the screen
		  Reporter.log(" Login Successfull!");
		  
	}
	
	//6. Function to logout from the collaborator
	public void _logOut(WebDriver driver) {
		  //Click on logout
		  WebElement logoutOption = driver.findElement(By.xpath("//*[@id=\"ddf_event_0\"]"));
		  logoutOption.click();
		  
		  // Print a Log Out message to the screen
		  Reporter.log(" Logout Successfull!");
		  
	}
	
	//7. Function to close the web browser
	public void _closeBrowser(WebDriver driver) {
		  // Close the driver
		  driver.quit();
		  Reporter.log("Browser closed");
		  
	}
	
	//8.Function to get the URL
	public String getUrl() {
		String url = _url;
		return url;
	}
}
