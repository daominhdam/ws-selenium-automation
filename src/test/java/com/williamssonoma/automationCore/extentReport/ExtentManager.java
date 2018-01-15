package com.williamssonoma.automationCore.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.williamssonoma.williamsSonomaTestAutomation.BaseTestCase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.IOException;

public class ExtentManager {
    private static ExtentReports extent;
	public static String filePath = System.getProperty("user.dir") + "/target/ExtentReports.html";

	public static synchronized ExtentReports getExtent() {
		if (extent == null) {
			extent = new ExtentReports();
			extent.attachReporter(getHtmlReporter());
/*			if (System.getenv("ExtentX").equalsIgnoreCase("true")) {
        extent.attachReporter(getExtentXReporter());
			}*/
			extent.setSystemInfo("Selenium Java Version", "2.53.0");
			extent.setSystemInfo("Environment", "Prod");
			extent.setSystemInfo("AppiumVersion", "4.0.0");

		}

		return extent;
	}
	private static ExtentHtmlReporter getHtmlReporter() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);

		// make the charts visible on report open
		htmlReporter.config().setChartVisibilityOnOpen(true);

		// report title
		//String documentTitle = prop.getProperty("documentTitle", "aventstack - Extent");
		htmlReporter.config().setDocumentTitle("WSSeleniumTestReport");
		htmlReporter.config().setReportName("ExtentReports");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		return htmlReporter;
	}




}
