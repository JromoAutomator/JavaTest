package com.Keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface webObjects {
	//Buttons
	public void button_Click(WebElement seleniumElement);

	//TextBox
	public void textBox_EnterText(WebElement seleniumElement,String strText);
	
	//Object Common
	public boolean element_isVisible(WebElement seleniumElement);
	public boolean element_isEnabled(WebElement seleniumElement);
	public String element_Getlabel(WebElement seleniumElement) ;
	public void element_GotoElement(WebElement seleniumElement, WebDriver driver);
}
