package com.williamssonoma.automationCore.listeners;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;

import org.testng.*;

public class ScreenshotListener extends TestListenerAdapter {

    public static WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        if(!result.isSuccess()){
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
                File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
                FileUtils.copyFile(scrFile, destFile);
                Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath() + "' height='100' width='100'/>screenshot </a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}