package com.williamssonoma.automationCore.listeners;

import com.williamssonoma.automationCore.util.reportngUtil.Screenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import Logger.Log;

import static com.williamssonoma.automationBaseClasses.BrowserDriver.getCurrentDriver;

public class TestNGTestListener extends TestListenerAdapter {

    WebDriver driver=null;


    @Override
    public void onStart(ITestContext testContext) {
        super.onStart(testContext);
        if (testContext.getAllTestMethods().length > 0) {
            Log.info(testContext.getAllTestMethods()[0]
                    .getRealClass().getSimpleName() + " start");
        }

    }
    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        Reporter.setCurrentTestResult(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        Log.info("test " + result.getTestClass().getTestName() + "-->"+ result.getMethod().getMethodName() + " succeeded ");
    }

    @Override
    public void onTestFailure(ITestResult result) {
       /* System.setProperty("org.uncommons.reportng.escape-output", "false");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        driver= getCurrentDriver();
        if(!result.isSuccess()){
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
                File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
                FileUtils.copyFile(scrFile, destFile);
                // Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/>screenshot </a>");
                String absolute = destFile.getAbsolutePath();
                int beginIndex = absolute.indexOf(".");
                String relative = absolute.substring(beginIndex).replace(".\\","");
                String screenShot = relative.replace('\\','/');


                Reporter.log("<a href=\"" + screenShot + "\"><p align=\"left\">Error screenshot at " + new Date()+ "</p>");
                Reporter.log("<p><img width=\"1024\" src=\"" + destFile.getAbsoluteFile()  + "\" alt=\"screenshot at " + new Date()+ "\"/></p></a><br />");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }

}