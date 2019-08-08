package com.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.Keywords.webElements_Keywords;

public class ManageDFP_Applicants extends webElements_Keywords{

	@FindBy(xpath="//*[@id='submissions']/div/div[2]/table/thead/tr/th[@title='0 Selected']")
    public WebElement chkSelectAll;
	
	@FindBy(linkText="Download")
    public WebElement btnDownload;
	
	@FindBy(linkText="Download selected as zip")
    public WebElement btnDownloadasZip;
	
	@FindBy(xpath="//*[@id='submissions']/div/div[3]/span/a[@class='next']")
    public WebElement btnNext;
	
	@FindBy(xpath="//*[@id='submissions']/div/div[3]/span/span[@class='total']")
    public WebElement lblTotalRows;
	
	@FindBy(xpath="//*[@id=‘container']/div[1]/div/div/ul[2]/li[3]/a")
    public WebElement btnUserprofile;
	
	@FindBy(xpath="//*[@id='container']/div[1]/div/div/ul[2]/li[3]/ul/li[11]/a[@title=\"Sign out”]")
    public WebElement btnSignOut;
	
	public ManageDFP_Applicants(WebDriver extdriver,int intTimeOut) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,intTimeOut), this);
	}
	
	public ManageDFP_Applicants(WebDriver extdriver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,100), this);
	}
}
