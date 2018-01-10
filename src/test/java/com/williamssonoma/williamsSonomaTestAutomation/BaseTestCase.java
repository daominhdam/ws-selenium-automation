package com.williamssonoma.williamsSonomaTestAutomation;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.williamssonoma.automationBaseClasses.CreateBrowser;
import com.williamssonoma.automationCore.extentReport.ExtentManager;
import com.williamssonoma.automationCore.extentReport.ExtentTestManager;
import com.williamssonoma.automationCore.util.GetScreenShot;
import com.williamssonoma.automationCore.util.verificationServices.Verifications;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseTestCase extends TestListenerAdapter {
	public static WebDriver driver;
	public ExtentTest testReporter;

	@BeforeSuite
	public void beforeSuite() throws IOException{
			

	}
	@AfterSuite
	public void afterSuite() {
		driver.quit();
		ExtentManager.getInstance().flush();
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
	public void beforeMethod(Method caller)
	{
		ExtentTestManager.startTest(caller.getName(), "This is a simple test.");
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
			if (result.isSuccess()) {
				ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
			}
			else if (result.getStatus() == ITestResult.FAILURE) {
				ExtentTestManager.getTest().log(LogStatus.FAIL, "Test failed");
			}
			else if (result.getStatus() == ITestResult.SKIP) {
				ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped");
			}

			ExtentTestManager.endTest();
			ExtentManager.getInstance().flush();

	/*	Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String methodName = result.getName();
		if(!result.isSuccess()){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/screenshots";
				File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
				FileUtils.copyFile(scrFile, destFile);
				Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/>screenshot </a>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}


	@AfterTest()
	 public void teardown(){
		if(driver==null) {
			driver.close();
		}

		}
	


	}




