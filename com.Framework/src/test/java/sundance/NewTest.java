package sundance;

import org.testng.annotations.Test;

import com.PageFactory.Dashboard;
import com.PageFactory.DownloadPage;
import com.PageFactory.LoginPage;
import com.PageFactory.ManageDFP_Applicants;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.testng.annotations.BeforeTest;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {
	private WebDriver chromeDriver;
	private String zipFileName;
	private String unzipFile;
	LoginPage NewLogin;
	Dashboard newDashbard;
	ManageDFP_Applicants AppPage;
	DownloadPage NewDownloadpage;
	
  @Test
  public void GetAppData() throws InterruptedException {
	  NewLogin = new LoginPage(this.chromeDriver,50);
	  newDashbard = new Dashboard(this.chromeDriver,50);
	  AppPage = new ManageDFP_Applicants(this.chromeDriver,10);
	  NewDownloadpage= new DownloadPage(this.chromeDriver,10);
	  
	  NewLogin.element_isVisible(NewLogin.txtEmail);
	  NewLogin.textBox_EnterText(NewLogin.txtEmail,"temp1@sundance.org");
	  NewLogin.textBox_EnterText(NewLogin.txtPWD,"Sunshine2019!");
	  NewLogin.button_Click(NewLogin.btnSignin);
	  
	  if(newDashbard.element_isVisible(newDashbard.mnNavigationMenu)) {
		  newDashbard.button_Click(newDashbard.btnManageyoursite);
		  newDashbard.button_Click(newDashbard.btnDFP_Applicants);
		  if(AppPage.element_isVisible(AppPage.chkSelectAll)) {
			  String strTotalRows = AppPage.element_Getlabel(AppPage.lblTotalRows);
			  int intTotalRows = Integer.parseInt(strTotalRows)/100;
			  int remainrows = Integer.parseInt(strTotalRows)%100;
			  System.out.println(remainrows);
			  System.out.println(intTotalRows);
			  if(remainrows>0) {
				  intTotalRows++;
			  }
			  //start the loop here
			  for (int i = 0; i < intTotalRows; i++) {
				  AppPage.element_GotoElement(AppPage.chkSelectAll, this.chromeDriver);
				  Thread.sleep(2000);
				  AppPage.button_Click(AppPage.chkSelectAll);
				  AppPage.button_Click(AppPage.btnDownload);
				  AppPage.button_Click(AppPage.btnDownloadasZip);
				  String windowHandle = this.chromeDriver.getWindowHandle();
				  ArrayList<String> tabs = new ArrayList<String> (this.chromeDriver.getWindowHandles());
				  System.out.println(tabs.size());
				  this.chromeDriver.switchTo().window(tabs.get(1));
				  System.out.println("preparing file to download");
				  do {
				  }while(NewDownloadpage.element_isVisible(NewDownloadpage.lblpleasewait));
				  if(NewDownloadpage.element_isVisible(NewDownloadpage.btnClickHere)) {
					  System.out.println("file ready...downloading...");
					  NewDownloadpage.button_Click(NewDownloadpage.btnClickHere);
					  zipFileName=this.waitfordownload(this.chromeDriver);
					  System.out.println("download completed...unziping file");
					  unzipFile=this.unzipFile(zipFileName);
					  System.out.println("unzip completed...files ready on "+unzipFile);
					  this.chromeDriver.close();
				  }else {
					  System.out.println("Something went wring, ending test");
				  }
				  
				  this.chromeDriver.switchTo().window(windowHandle);
				  AppPage.element_GotoElement(AppPage.btnNext, this.chromeDriver);
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
  }
  
  
  @BeforeTest
  public void beforeTest() {
	  if(!System.getProperty("os.name").equals("Mac OS X")){
		  System.setProperty("webdriver.chrome.driver", "Path to .exe");
		  System.out.println("Running on Windows..");
	  }
	  this.chromeDriver = new ChromeDriver();
	  this.chromeDriver.manage().window().maximize();
	  this.chromeDriver.get("http://applications.sundance.org");
  }
  

  @AfterTest
  public void afterTest() {
	  AppPage.button_Click(AppPage.btnUserprofile);
	  AppPage.button_Click(AppPage.btnSignOut);
	  try {
		Thread.sleep(2000);
	  } catch (InterruptedException e) {
		e.printStackTrace();
	  }
	  this.chromeDriver.quit();
  }
  
  
  public String waitfordownload(WebDriver driver) throws InterruptedException {
	  int x=0;
      String mainWindow = driver.getWindowHandle();
      JavascriptExecutor js = (JavascriptExecutor)driver;
      js.executeScript("window.open()");
      for(String winHandle : driver.getWindowHandles()){
          driver.switchTo().window(winHandle);
      }
      driver.get("chrome://downloads");

      JavascriptExecutor js1 = (JavascriptExecutor)driver;
      Long percentage = (long) 0;
      while (percentage!= 100 && x<=5) {
          try {
              percentage = (Long) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#progress').value");
          }catch (Exception e) {
        }
          Thread.sleep(1000);
          if(percentage==0) {
        	  x++;
          }
      }
      String fileName = (String) js1.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('div#content #file-link').text");
      System.out.println("File Name :-" + fileName);
      driver.close();
     // switch back to main window
      driver.switchTo().window(mainWindow);
      return fileName;
  }
  
  
  public String unzipFile(String source) {
	  String downloadfolder="/Users/jesusromollamas/Downloads/";
	  String destination = "/Users/jesusromollamas/Desktop/Files/";   

	  try {
	      ZipFile zipFile = new ZipFile(downloadfolder+source);
	      zipFile.extractAll(destination);
	  } catch (ZipException e) {
	      e.printStackTrace();
	  }
	  return destination;
  }

}
