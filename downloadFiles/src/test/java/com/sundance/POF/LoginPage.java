package com.sundance.POF;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.Keywords.webElements_Keywords;

public class LoginPage extends webElements_Keywords {

	@FindBy(id="id_email")
    public WebElement txtEmail;
	
	@FindBy(id="id_password")
    public WebElement txtPWD;
	
	@FindBy(id="frontpage-sign-in")
    public WebElement btnSignin;
	
	public LoginPage(WebDriver extdriver,int intTimeOut) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,intTimeOut), this);
	}
	
	public LoginPage(WebDriver extdriver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(extdriver,100), this);
	}
}
