package com.williamssonoma.automationCore.listeners;

import com.aventstack.extentreports.Status;
import com.williamssonoma.automationCore.extentReport.ExtentManager;
import com.williamssonoma.automationCore.extentReport.ExtentTestManager;
import com.williamssonoma.automationCore.util.reportngUtil.CustomReportNgUtils;
import com.williamssonoma.automationCore.util.reportngUtil.Screenshot;
import com.williamssonoma.williamsSonomaTestAutomation.BaseTestCase;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.uncommons.reportng.HTMLReporter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.williamssonoma.automationBaseClasses.BrowserDriver.getCurrentDriver;

public class TestListenerAdapter extends HTMLReporter implements ITestListener, IConfigurationListener
{
    protected static final CustomReportNgUtils REPORT_NG_UTILS = new CustomReportNgUtils();
    WebDriver driver=null;

    @Override
    protected VelocityContext createContext()
    {
        VelocityContext context = super.createContext();

        // VelocityContext has three properties: meta, utils, messages
        // - see AbstractReporter.createContext()
        context.put("utils", REPORT_NG_UTILS);

        return context;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    /** Invoked when test method (method with annotation @Test) fails. */
    @Override
    public void onTestFailure(ITestResult result) {
        if (result != null) {
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            String methodName = result.getName();
            driver= getCurrentDriver();
            if (!result.isSuccess()) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/screenshots";
                    File destFile = new File((String) reportDirectory + "/failure_screenshots_reportng/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
                    FileUtils.copyFile(scrFile, destFile);
                    String srcUrl = driver.getCurrentUrl();
                   /* JavascriptExecutor js = (JavascriptExecutor) driver;
                    String srcUrl=js.executeScript("return window.location.href").toString();*/
                    Screenshot screenshot = new Screenshot(destFile, srcUrl);
                    result.setAttribute(Screenshot.KEY, screenshot);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
            ExtentManager.getExtent().flush();
    }

    @Override
    public void onConfigurationSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onConfigurationFailure(ITestResult iTestResult) {

    }

    @Override
    public void onConfigurationSkip(ITestResult iTestResult) {

    }
}