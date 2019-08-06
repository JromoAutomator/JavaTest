package com.RegressionTest;

import org.testng.annotations.Test;

import com.Keywords.webElements_Keywords;
import com.PageFactory.HomePage;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	private WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  this.driver = new ChromeDriver();
	  this.driver.manage().window().maximize();
	  this.driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
  }
	
  @Test
  public void userLogin() {
	  HomePage LoginPage = new HomePage(this.driver,50);
	  LoginPage.element_isVisible(LoginPage.txtEmail);
	  LoginPage.textBox_EnterText(LoginPage.txtEmail, "j2558@gmail.com");
  }
 

  @AfterClass
  public void afterClass() {
	  this.driver.quit();
  }

}
