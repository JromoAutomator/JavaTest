package com.JavaSimpleTest.TestNG_java;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class NewTest {
	WebDriver driver;
	
	
  @Test
  public void f() throws InterruptedException {
	  driver.get("https://www.google.com");
	  driver.manage().window().maximize();
	  Thread.sleep(5000);
	  driver.quit();
  }
  
  @BeforeClass
  public void beforeClass(){
	  driver = new ChromeDriver();
  }

  @AfterClass
  public void afterClass() {
  }

}
