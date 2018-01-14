package com.williamssonoma.williamsSonomaTestAutomation;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.williamssonoma.automationBaseClasses.CreateBrowser;
import com.williamssonoma.automationCore.extentReport.ExtentManager;
import com.williamssonoma.automationCore.extentReport.ExtentTestManager;
import com.williamssonoma.automationCore.listeners.TestListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BaseTestCase extends TestListener {
	public static WebDriver driver;
	ExtentReports extent;
	public static ExtentTest test;
    public WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void beforeSuite() throws IOException{
			

	}

	
	@BeforeTest
	public void beforeTest(){
		if(driver==null) {
			driver=CreateBrowser.getBrowser();
		}
        extent=ExtentManager.getInstance();
		extent.addSystemInfo("Environment", "Automation QA");

	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}


	@BeforeMethod
	public void beforeMethod(Method caller)
	{
		test=extent.startTest(caller.getName(), "Williams Sonoma Cookware Page Test.");
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
		Calendar calendar = Calendar.getInstance();
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
		}
		if (result.isSuccess()) {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
		}
		else if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Test failed");
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped");
		}
		extent.endTest(test);
		test.log(LogStatus.INFO, "Calling endTest() method of ExtentReports to stop capturing information about the test log");
		extent.flush();
		test.log(LogStatus.INFO, "Calling flush() method of ExtentReports to push/write everything to the document");
	}


	@AfterTest()
	 public void teardown(){

		if(driver==null) {
			driver.close();
		}

		}

	@AfterSuite
	public void afterSuite() {
		driver.quit();
		extent.flush();
	}

	}




