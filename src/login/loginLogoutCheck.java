/* 
 * TEST SCENARIO 1 - Testing the different functionalities on the home page
 * Test Case 1a,1b: loginLogoutCheck - Checking Login-Logout Feature
 * Pre-Conditions: The credentials provided under setCredentials() method should Exist
 * Output: The user should be able to successfully login and logout
 */
package login;

import org.testng.annotations.Test;

import utility.utilFunctions;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterSuite;

public class loginLogoutCheck {
  public RemoteWebDriver driver;
  
  @DataProvider(name = "Authentication")
  public static Object[][] setCredentials(){
	  return new Object[][] {{"admin","dgo_col0317"},{"ghhgjg","gjhggg"}};	  
  }
  
  @Test(priority = 0,groups= {"Smoke Testing"})
  @Parameters({"browserType"})
  public void openBrowser(String browserType) throws MalformedURLException{
	  Capabilities desiredCapabilities = null;
	  if(browserType.equals("Firefox")) {
		  desiredCapabilities = DesiredCapabilities.firefox();
	  }
	  else if(browserType.equals("Chrome")) {
		  desiredCapabilities = DesiredCapabilities.chrome();
	  }
	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
      //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 
      //Launch collaborator
      utilFunctions util = new utilFunctions();
      String url = util.getUrl();
      driver.get(url);
      
      Reporter.log("Browser opened successfully.");
  }
  
  @Test(priority = 1, dependsOnMethods = {"openBrowser"}, groups= {"Smoke Testing"}, dataProvider = "Authentication")
  public void logInLogOut(String sUsername, String sPassword) {
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
			  
	 //Click on logout
	 WebElement logoutOption = driver.findElement(By.xpath("//*[@id=\"ddf_event_0\"]"));
	 logoutOption.click();
			  
	 // Print a Log Out message to the screen
	 Reporter.log(" Logout Successfull!");
  }
  
  @Test(priority = 2, dependsOnMethods = {"logInLogOut"}, groups= {"Smoke Testing"})
  public void closeBrowser() {
	  // Close the driver
	  driver.quit();
	  Reporter.log("Browser closed");
	  
  }
  @BeforeClass
  public void beforeClass() {
	  Reporter.log("Start of Class loginLogoutCheck");
  }

  @AfterClass
  public void afterClass() {
	  Reporter.log("End of Class loginLogoutCheck");
  }

  @BeforeSuite
  public void beforeSuite() {
	  Reporter.log("--------Start of Test Scenario 1-----------");
  }

  @AfterSuite
  public void afterSuite() {
	  Reporter.log("---------End of Test Scenario 1------------");
  }
}
