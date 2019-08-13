package com.mobile;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

public class MobileNewTest {
	WebDriver driver;
	
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeTest
  public void beforeTest() throws MalformedURLException {
	  
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("BROWSER_NAME", "Android");
	capabilities.setCapability("VERSION", "8.1"); 
	capabilities.setCapability("deviceName","Nexus_5X_API_27");
	capabilities.setCapability("platformName","Android");
	capabilities.setCapability("appPackage", "com.android.calculator2");
	capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
	driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	System.out.println("working");
	  
	  
	

  }

  @AfterTest
  public void afterTest() {
  }

}
