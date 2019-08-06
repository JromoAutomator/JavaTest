package com.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.Keywords.webElements_Keywords;

public class HomePage extends webElements_Keywords{
	
	@FindBy(id="email")
    public WebElement txtEmail;
	
	@FindBy(id="passwd")
    public WebElement txtPWD;
	
	@FindBy(id="SubmitLogin")
    public WebElement btnSignin;
	
	public HomePage(WebDriver extdriver,int intTimeOut) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,intTimeOut), this);
	}
	
	public HomePage(WebDriver extdriver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,100), this);
	}

}
