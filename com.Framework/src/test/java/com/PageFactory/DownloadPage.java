package com.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.Keywords.webElements_Keywords;

public class DownloadPage extends webElements_Keywords{

	@FindBy(xpath="//*[@id='message']/div/a")
    public WebElement btnClickHere;
	
	@FindBy(id="progress-inner")
	public WebElement lblpleasewait;
	
	
	public DownloadPage(WebDriver extdriver,int intTimeOut) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,intTimeOut), this);
	}
	
	public DownloadPage(WebDriver extdriver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,100), this);
	}

}
