package com.sundance.downloadFiles;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.sundance.POF.Dashboard;
import com.sundance.POF.DownloadPage;
import com.sundance.POF.LoginPage;
import com.sundance.POF.Manage_Applicants;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class downloadFilesTest {
	private WebDriver chromeDriver;
	private String zipFileName;
	private String unzipFile;
	private String Chromepath;
	private int PageObjectTiemOut=30;
	private String App="http://applications.sundance.org";
	private String ApplicationData="2019_New_Frontier_Story_Lab";
	private String User_Email="temp1@sundance.org";
	private String User_pwd="Sunshine2019!";
	private String downloadfolder="/Users/jesusromollamas/Downloads/";
	private String destination = "/Users/jesusromollamas/Desktop/Files/";   
	
	
  @Test
  public void f() {
	  downloadFilesTest filesApp= new downloadFilesTest();
		
		if(!System.getProperty("os.name").equals("Mac OS X")){
			  System.setProperty("webdriver.chrome.driver", filesApp.Chromepath);
			  System.out.println("Running on Windows..");
		  }
		filesApp.chromeDriver = new ChromeDriver();
		filesApp.chromeDriver.manage().window().maximize();
		filesApp.chromeDriver.get(filesApp.App);
		
		LoginPage NewLogin = new LoginPage(filesApp.chromeDriver,filesApp.PageObjectTiemOut);
		Dashboard newDashbard = new Dashboard(filesApp.chromeDriver,filesApp.PageObjectTiemOut);
		Manage_Applicants AppPage = new Manage_Applicants(filesApp.chromeDriver,filesApp.PageObjectTiemOut);
		DownloadPage NewDownloadpage= new DownloadPage(filesApp.chromeDriver,filesApp.PageObjectTiemOut);
		
		 
		NewLogin.element_isVisible(NewLogin.txtEmail);
		NewLogin.textBox_EnterText(NewLogin.txtEmail,filesApp.User_Email);
		NewLogin.textBox_EnterText(NewLogin.txtPWD,filesApp.User_pwd);
		NewLogin.button_Click(NewLogin.btnSignin);
		
		if(newDashbard.element_isVisible(newDashbard.mnNavigationMenu)) {
		  newDashbard.button_Click(newDashbard.btnManageyoursite);
		  newDashbard.button_Click(filesApp.GetApplicationType(newDashbard));
		  if(AppPage.element_isVisible(AppPage.chkSelectAll)) {
			  String strTotalRows = AppPage.element_Getlabel(AppPage.lblTotalRows);
			  int intTotalRows = Integer.parseInt(strTotalRows)/100;
			  int remainrows = Integer.parseInt(strTotalRows)%100;
			  if(remainrows>0) {
				  intTotalRows++;
			  }
			  //start the loop here
			  for (int i = 0; i < intTotalRows; i++) {
				  AppPage.element_GotoElement(AppPage.chkSelectAll, filesApp.chromeDriver);
				  try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();
				}
				  AppPage.button_Click(AppPage.chkSelectAll);
				  AppPage.button_Click(AppPage.btnDownload);
				  AppPage.button_Click(AppPage.btnDownloadasZip);
				  String windowHandle = filesApp.chromeDriver.getWindowHandle();
				  ArrayList<String> tabs = new ArrayList<String> (filesApp.chromeDriver.getWindowHandles());
				  System.out.println(tabs.size());
				  filesApp.chromeDriver.switchTo().window(tabs.get(1));
				  System.out.println("preparing file to download");
				  do {
				  }while(NewDownloadpage.element_isVisible(NewDownloadpage.lblpleasewait));
				  if(NewDownloadpage.element_isVisible(NewDownloadpage.btnClickHere)) {
					  System.out.println("file ready...downloading...");
					  NewDownloadpage.button_Click(NewDownloadpage.btnClickHere);
					  filesApp.zipFileName=filesApp.waitfordownload(filesApp.chromeDriver);
					  System.out.println("download completed...unziping file");
					  //filesApp.unzipFile=filesApp.unzipFile(filesApp.zipFileName);
					  //System.out.println("unzip completed...files ready on "+filesApp.unzipFile);
					  filesApp.chromeDriver.close();
				  }else {
					  System.out.println("Something went wring, ending test");
				  }
				  
				  filesApp.chromeDriver.switchTo().window(windowHandle);
				  AppPage.element_GotoElement(AppPage.btnNext, filesApp.chromeDriver);
				  AppPage.button_Click(AppPage.btnNext);
				  do {
				  }while(!AppPage.element_isEnabled(AppPage.chkSelectAll));
			  }
			  //end loop here
		  }else {
			  
			  System.out.println("Something went wring, ending test");
			  }
		}else {
			System.out.println("Something went wring with the login, ending test");
		}
			
		AppPage.button_Click(AppPage.btnUserprofile);
		AppPage.button_Click(AppPage.btnSignOut);
		try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
		filesApp.chromeDriver.quit();
  }
  
  public String waitfordownload(WebDriver driver){
      String mainWindow = driver.getWindowHandle();
      JavascriptExecutor js = (JavascriptExecutor)driver;
      js.executeScript("window.open()");
      for(String winHandle : driver.getWindowHandles()){
          driver.switchTo().window(winHandle);
      }
      driver.get("chrome://downloads");
      JavascriptExecutor js1 = (JavascriptExecutor)driver;
      Long percentage = (long) 0;
      while (percentage!= 100) {
          try {
        	  try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
              percentage = (Long) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#progress').value");
          }catch (Exception e) {
        	  try {Thread.sleep(3000);} catch (InterruptedException ex) {ex.printStackTrace();}
        	  percentage=(long) 100;
        	  
        }
          try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
      }
      String fileName = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').text");
      System.out.println("File Name :-" + fileName);
      driver.close();
     // switch back to main window
      driver.switchTo().window(mainWindow);
      return fileName;
  }
  
  
  public WebElement GetApplicationType(Dashboard intDashbard) {
	  WebElement returnObject=null;
	  switch(ApplicationData) {
		  case "DFP_Applicants":
			  returnObject = intDashbard.btnDFP_Applicants;
			  break;
		  case "2019_New_Frontier_Story_Lab":
			  returnObject = intDashbard.btn2019_New_Frontier_Story_Lab;
			  break;
		  default:
			  returnObject=null;
			  break;
	  }
	  return returnObject;
  }

}
