package com.williamssonoma.automationBaseClasses;

import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.williamssonoma.automationBaseClasses.Constants.Browsers;


public class CreateBrowser {
	
	private static final String BROWSER_PROP_KEY = "browser";
	
	/**
	 * creates the browser driver specified in the system property "browser"
	 * if no property is set then a chrome browser driver is created.
	 * The allow properties are safari and chrome
	 * e.g to run with safari, pass in the option -Dbrowser=safari at runtime
	 * @return WebDriver
	 */
	public static WebDriver getBrowser() {
		Browsers browser;
		WebDriver dr;
		if(System.getProperty(BROWSER_PROP_KEY)==null){
			browser = Browsers.CHROME;
		}else{
			browser = Browsers.browserForName(System.getProperty(BROWSER_PROP_KEY));
		}		
		switch(browser){
			case CHROME:
				dr = createChromeDriver();
				break;
			case SAFARI:
				dr = createSafariDriver();
				break;
				default:
					dr = createChromeDriver();
					break;
		}
		addAllBrowserSetup(dr);
		return dr;
	}

	private static WebDriver createSafariDriver() {
		return new SafariDriver();
	}

	private static WebDriver createChromeDriver() {
		//below code lets you switch between a local driver and the grid:
		String isRemote = System.getProperty("remote");
		if("true".equalsIgnoreCase(isRemote)) {
			DesiredCapabilities chrome = DesiredCapabilities.chrome();
			chrome.setVersion("32");
			chrome.setPlatform(Platform.ANY);
			//The URL in the below code should be the address of your Selenium Grid hub.
			URL url= null;
			try {
				url = new URL("http://localhost:4444/wd/hub");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return new RemoteWebDriver(url, chrome);
		}
			else{
			System.setProperty("webdriver.chrome.driver", "src/test/resources/server/chromedriver.exe");
			return new ChromeDriver();
			}



	}
	
	
	private static void addAllBrowserSetup(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(dim);
	}

}