package com.williamssonoma.automationCore.util;

import com.williamssonoma.automationCore.exceptions.ScreenShotException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetScreenShot {
     
    public static String createScreenshot(WebDriver driver,String screenShotName)
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\screenshots\\"+screenShotName+".png";
        File destination = new File(dest);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dest;
    }


    public static void captureScreenshot(WebDriver driver,String screenshotName)
    {
        try
        {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File source=ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./screenshots/"+screenshotName+".png"));

            System.out.println("Screenshot taken");
        }
        catch (Exception e) {
           throw new ScreenShotException(e);
        }
    }




}