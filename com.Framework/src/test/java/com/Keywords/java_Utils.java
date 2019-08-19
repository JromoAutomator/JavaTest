package com.Keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class java_Utils {
	
	public WebDriver GetOS(Enums.Browser enBrowser) {
		String browserDriver=null;
		WebDriver driver=null;
		switch(enBrowser) {
			case CHROME:
				browserDriver="webdriver.chrome.driver";
				switch(System.getProperty("os.name")) {
				case "Linux":
					System.setProperty(browserDriver, "./drivers/chromedriver");
					System.out.println("Running on Linux..");
					break;
				case "Mac OS":
					System.out.println("Running on Mac..");
					break;
				default:
					System.setProperty(browserDriver, "./drivers/chromedriver.exe");
					System.out.println("Running on Windows..");
					break;
				}
				break;
			case FIREFOX:
				browserDriver="webdriver.gecko.driver";
				break;
			case INTERNET_EXPLORER:
				browserDriver="webdriver.ie.driver";
				break;
			case EDGE:
				browserDriver="webdriver.edge.driver";
				break;
			default:
				break;
		}
		
		
		
		return driver;
	}

}
