package com.RegressionTest;

import org.testng.annotations.Test;

import com.PageFactory.HomePage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	private WebDriver chromeDriver;
	private WebDriver firefoxDriver;

  @BeforeTest(groups = { "Regression" })
  public void beforeSuite() {
	  this.chromeDriver = new ChromeDriver();
	  this.chromeDriver.manage().window().maximize();
	  this.chromeDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
  }
  
  @BeforeTest(groups = { "SmokeTest" })
  public void beforeSuite2() {
	  this.firefoxDriver = new FirefoxDriver();
	  this.firefoxDriver.manage().window().maximize();
	  this.firefoxDriver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
  }
	
  @Test(groups = { "Regression" },priority=2,description="this is a Test to login into the application")
  public void userLogin() {
	  HomePage LoginPage = new HomePage(this.chromeDriver,50);
	  LoginPage.element_isVisible(LoginPage.txtEmail);
	  LoginPage.textBox_EnterText(LoginPage.txtEmail, "j2558@gmail.com");
  }
 
  @Test(groups = { "Regression" },priority=2,description="this is a Test to login into the application")
  public void Homepage_Links() {
	  HomePage LoginPage = new HomePage(this.chromeDriver,50);
	  LoginPage.element_isVisible(LoginPage.txtEmail);
	  LoginPage.textBox_EnterText(LoginPage.txtEmail, "j2558@gmail.com");
  }
  
  @Test(groups = { "SmokeTest" },priority=1,description="this is a Test to login into the application",dataProvider = "getdata")
  public void RunwithData(int index,String data) {
		  System.out.println("index= "+index+" , data = "+data);	
  }
  
 
  @AfterClass(groups = { "Regression" })
  public void afterSuite() {
	  this.chromeDriver.quit();
  }
  
  @AfterClass(groups = { "SmokeTest" })
  public void afterSuite2() {
	  this.firefoxDriver.quit();
  }
  
  @DataProvider
  public Object[][] getdata(){
	  return new Object[][] {{1,"one.1"},{2,"Test"}};
  }

}
