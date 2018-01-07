package com.williamssonoma.automationCore.util;

import com.williamssonoma.automationBaseClasses.BrowserDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
 
public class GetScreenShot {
     
    public static String capture(WebDriver driver,String screenShotName) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\screenshots\\"+screenShotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);        
                     
        return dest;
    }


    public static String captureScreenShotInCaseOfFailure(ITestResult result) {

            String screenShotFolder = "screenshots";
            File screenShotTargetFile=null;
            if (!result.isSuccess()) {
                File screenShotSourceFile = ((TakesScreenshot) BrowserDriver.getCurrentDriver()).getScreenshotAs(OutputType.FILE);
                try {
                    createFolder(screenShotFolder);
                    String screenshotFileName = result.getMethod().getMethodName();
                    screenShotTargetFile = getTargetFile(screenShotFolder, screenshotFileName, "png");
                    FileUtils.copyFile(screenShotSourceFile, screenShotTargetFile);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return screenShotTargetFile != null ? screenShotTargetFile.getPath() : null;

    }



    private static void createFolder(String folderName) throws IOException {
        if (!(new File(folderName).exists())) new File(folderName).mkdir();
    }

    private static File getTargetFile(String folderName, String fileName, String ext) throws IOException {
        String rootPath = new File(".").getCanonicalPath();
        String fullPath = String.format("%s//%s//%s.%s", rootPath, folderName, fileName, ext);
        return new File(fullPath);
    }


}