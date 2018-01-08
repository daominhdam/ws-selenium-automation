package com.williamssonoma.williamsSonomaTestAutomation;

import com.williamssonoma.automationBaseClasses.BrowserDriver;
import com.williamssonoma.automationBaseClasses.CreateBrowser;
import com.williamssonoma.automationCore.util.GetScreenShot;
import com.williamssonoma.automationCore.util.VerificationService.Verifications;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTestCase extends Verifications {
	public static WebDriver driver;

	@BeforeSuite
	public void beforeSuite() throws IOException{
			

	}
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}
	
	@BeforeTest
	public void beforeTest(){
		if(driver==null) {
			driver=CreateBrowser.getBrowser();
		}
	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}


	@BeforeMethod
	public void beforeMethod(){
	
	}
	@AfterMethod()
	public void afterMethod(ITestResult result){
		if(ITestResult.FAILURE==result.getStatus())
			GetScreenShot.captureScreenshot(driver, result.getMethod().getMethodName());

	}
	@AfterTest()
	 public void teardown(){
		BrowserDriver.getCurrentDriver().close();
		}
	


	}




