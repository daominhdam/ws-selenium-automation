package com.williamssonoma.williamsSonomaTestAutomation;

import com.williamssonoma.automationBaseClasses.BrowserDriver;
import com.williamssonoma.automationBaseClasses.CreateBrowser;
import com.williamssonoma.automationCore.Reporter.ExtentReportsGenerator;
import com.williamssonoma.automationCore.util.VerificationService.Verifications;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class BaseTestCase extends Verifications {
	public static WebDriver driver;
	@BeforeSuite
	public void beforeSuite() throws IOException{
			

	}
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}
	
	@BeforeTest
	public void beforeTest(){
		if(driver==null) {
			driver=CreateBrowser.getBrowser();
		}
	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}


	@BeforeMethod
	public void beforeMethod(){
	
	}
	@AfterTest()
	 public void teardown(ITestResult result){
	        captureScreenShotInCaseOfFailure(result);
	        BrowserDriver.getCurrentDriver().close();

	    }
	
	
    protected void captureScreenShotInCaseOfFailure(ITestResult result) {
        if (result.isSuccess()) {
            return;
        }
        String screenShotFolder = "screenshots";
        File screenShotSourceFile = ((TakesScreenshot) BrowserDriver.getCurrentDriver()).getScreenshotAs(OutputType.FILE);
        try {
            createFolder(screenShotFolder);
            String fileName = result.getMethod().getMethodName();
            File screenShotTargetFile = getTargetFile(screenShotFolder, fileName, "png");
            FileUtils.copyFile(screenShotSourceFile, screenShotTargetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }        
       
      }
	
	 private void createFolder(String folderName) throws IOException {
         if (!(new File(folderName).exists())) new File(folderName).mkdir();
     }

     private File getTargetFile(String folderName, String fileName, String ext) throws IOException {
         String rootPath = new File(".").getCanonicalPath();
         String fullPath = String.format("%s//%s//%s.%s", rootPath, folderName, fileName, ext);
         return new File(fullPath);
     }
	

	}




