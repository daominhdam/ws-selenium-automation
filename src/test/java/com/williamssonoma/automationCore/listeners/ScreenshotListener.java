package com.williamssonoma.automationCore.listeners;

import com.williamssonoma.automationCore.util.reportngUtil.Screenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.williamssonoma.automationBaseClasses.BrowserDriver.getCurrentDriver;

public class ScreenshotListener extends TestListenerAdapter {

    WebDriver driver=null;

    @Override
    public void onTestFailure(ITestResult result) {
    if (result != null) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        driver = getCurrentDriver();
        if (!result.isSuccess()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/screenshots";
                File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
                FileUtils.copyFile(scrFile, destFile);
                String srcUrl = driver.getCurrentUrl();
                   /* JavascriptExecutor js = (JavascriptExecutor) driver;
                    String srcUrl=js.executeScript("return window.location.href").toString();*/
                Screenshot screenshot = new Screenshot(destFile, srcUrl);
                result.setAttribute(Screenshot.KEY, screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
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