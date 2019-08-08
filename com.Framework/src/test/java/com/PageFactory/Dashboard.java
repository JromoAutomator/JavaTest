package com.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.Keywords.webElements_Keywords;

public class Dashboard extends webElements_Keywords{

	@FindBy(id="navigation")
    public WebElement mnNavigationMenu;
	
	@FindBy(xpath="//*[@id='fixed']/div[1]/a[@href='#manage']")
    public WebElement btnManageyoursite;
	
	@FindBy(xpath="//*[@id='manage-pane']/div[3]/ul/li[23]/a[@title='DFP_Applicants']")
    public WebElement btnDFP_Applicants;
	
	public Dashboard(WebDriver extdriver,int intTimeOut) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,intTimeOut), this);
	}
	
	public Dashboard(WebDriver extdriver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,100), this);
	}

}
