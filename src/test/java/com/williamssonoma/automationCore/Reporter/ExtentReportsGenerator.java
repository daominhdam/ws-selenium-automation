package com.williamssonoma.automationCore.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.williamssonoma.automationCore.util.GetScreenShot;
import com.williamssonoma.automationCore.util.LoadProperties;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class ExtentReportsGenerator{

	public static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void setupReport()
	{
		extent=  ExtentManager.getInstance();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.fail(MarkupHelper.createLabel(result.getName(),ExtentColor.RED));

			String screenShotPath =GetScreenShot.captureScreenShotInCaseOfFailure(result);
			//To Print Whole Stack Trace in Result
			test.fail(result.getThrowable());
			if (screenShotPath!=null) {
				test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
			}

		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.pass(MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN));

		}

		else
		{
			test.skip(MarkupHelper.createLabel(result.getName(),ExtentColor.GREY));
			//To Print Whole Stack Trace in Result
			test.skip(result.getThrowable());
		}



	}


	@AfterSuite
	public void cleanUp()
	{
		extent.flush();
	}

}