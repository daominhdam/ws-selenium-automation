package com.williamssonoma.automationCore.retryAnalyzer;

import com.aventstack.extentreports.Status;
import com.williamssonoma.automationCore.extentReport.ExtentTestManager;
import com.williamssonoma.williamsSonomaTestAutomation.BaseTestCase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.IOException;


public class RetryAnalyzer implements IRetryAnalyzer{
	private int count = 0;
	private static int maxTry = 1; //Run the failed test 2 times

	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {                      //Check if test not succeed
			if (count < maxTry) {                            //Check if maxtry count is reached
				count++;                                     //Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
				extendReportsFailOperations(iTestResult);    //ExtentReports fail operations
				return true;                                 //Tells TestNG to re-run the test
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
		}
		return false;
	}

	public void extendReportsFailOperations (ITestResult iTestResult) {
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((BaseTestCase) testClass).getDriver();
		String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
		try {
			ExtentTestManager.getTest().log(Status.FAIL,"Test Failed"+
                    ExtentTestManager.getTest().addScreenCaptureFromPath(base64Screenshot));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}