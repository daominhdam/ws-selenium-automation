package com.williamssonoma.williamsSonomaTestAutomation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.williamssonoma.automationBaseClasses.CreateBrowser;
import com.williamssonoma.automationCore.extentReport.ExtentManager;
import com.williamssonoma.automationCore.extentReport.ExtentTestManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;
import com.williamssonoma.automationCore.util.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.williamssonoma.automationCore.util.GetScreenShot.createScreenshot;

public class BaseTestCase  extends TestListenerAdapter {
	public static WebDriver driver;
	ExtentReports extent;
	//public static ExtentTest test;
	public ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

    public WebDriver getDriver() {
		return driver;
	}

	@BeforeSuite
	public void beforeSuite() throws IOException{
			

	}

	@BeforeClass
	public synchronized void beforeClass() throws Exception {
		ExtentTest parent = ExtentTestManager.createTest(getClass().getName());
		parentTest.set(parent);
		//throw new Exception("Failed ******* ");
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
	public void beforeMethod(Method method)
	{
		//test=extent.startTest(caller.getName(), "Williams Sonoma Cookware Page Test.");
		ExtentTest child = parentTest.get().createNode(method.getName());
		test.set(child);

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
			ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		}
		else if (result.getStatus() == ITestResult.FAILURE) {
			String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);

			try {
				ExtentTestManager.getTest().log(Status.FAIL,result.getThrowable().toString()+
						ExtentTestManager.getTest().addScreenCaptureFromPath(base64Screenshot));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(Status.SKIP, "Test skipped");
		}

		ExtentManager.getExtent().flush();
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
	}

	}




