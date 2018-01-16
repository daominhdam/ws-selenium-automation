package com.williamssonoma.automationCore.retryAnalyzer;

import com.aventstack.extentreports.Status;
import com.williamssonoma.automationCore.extentReport.ExtentTestManager;
import com.williamssonoma.williamsSonomaTestAutomation.BaseTestCase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class RetryAnalyzer implements IRetryAnalyzer{
	//private int count = 0;
	private static int MAX_RETRY_COUNT = 1; //Run the failed test 2 times

	AtomicInteger count = new AtomicInteger(MAX_RETRY_COUNT);

	public boolean isRetryAvailable() {
		return (count.intValue() > 0);
	}

	/*@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) {                      //Check if test not succeed
			if (count < maxTry) {                            //Check if maxtry count is reached
				count++;                                     //Increase the maxTry count by 1
				iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
				extentReportsFailOperations(iTestResult);    //ExtentReports fail operations
				return true;                                 //Tells TestNG to re-run the test
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
		}
		return false;
	}*/

	public boolean retry(ITestResult result) {
		boolean retry = false;
		if (isRetryAvailable()) {
			System.out.println("Going to retry test case: " + result.getMethod() + ", " + (MAX_RETRY_COUNT - count.intValue() + 1) + " out of " + MAX_RETRY_COUNT);
			retry = true;
			count.decrementAndGet();
		}
		return retry;
	}



	public void extentReportsFailOperations (ITestResult iTestResult) {
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