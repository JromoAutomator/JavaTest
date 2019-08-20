package com.Keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class java_Utils {
	
	public WebDriver getDriver(Enums.Browser enBrowser) {
		String browserDriver=null;
		WebDriver driver=null;
		switch(enBrowser) {
			case CHROME:
				browserDriver="webdriver.chrome.driver";
				this.getOS(browserDriver,"./drivers/chromedriver");
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				browserDriver="webdriver.gecko.driver";
				this.getOS(browserDriver,"./drivers/geckodriver");
				driver = new FirefoxDriver();
				break;
			case INTERNET_EXPLORER:
				browserDriver="webdriver.ie.driver";
				this.getOS(browserDriver,"./drivers/chromedriver");
				driver = new InternetExplorerDriver();
				break;
			case EDGE:
				browserDriver="webdriver.edge.driver";
				this.getOS(browserDriver,"./drivers/chromedriver");
				driver = new EdgeDriver();
				break;
			case SAFARI:
				browserDriver="webdriver.edge.driver";
				this.getOS(browserDriver,"./drivers/chromedriver");
				driver = new SafariDriver();
				break;
			default:
				break;
		}
		
		
		
		return driver;
	}
	
	private void getOS(String browserDriver,String driverpath) {		
		switch(System.getProperty("os.name")) {
			case "Linux":
				System.setProperty(browserDriver, driverpath);
				System.out.println("Running on Linux..");
				break;
			case "Mac OS":
				System.out.println("Running on Mac..");
				break;
			default:
				System.setProperty(browserDriver, driverpath+".exe");
				System.out.println("Running on Windows..");
				break;
		}
	}

}
