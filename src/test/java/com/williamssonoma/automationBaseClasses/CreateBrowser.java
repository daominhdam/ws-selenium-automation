package com.williamssonoma.automationBaseClasses;

import com.williamssonoma.automationBaseClasses.Constants.Browsers;
import com.williamssonoma.automationCore.util.LoadProperties;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CreateBrowser {
	private static final Logger LOG = Logger.getLogger(CreateBrowser.class.getName());

	private static final String BROWSER_PROP_KEY = "browser";
	private static String PLATFORM;
	private static String BROWSER;
	private static String DRIVER_ROOT_DIR;
	private static String SELENIUM_HOST;
	private static String SELENIUM_PORT;
	private static String SELENIUM_REMOTE_URL;
	private static Dimension BROWSER_WINDOW_SIZE;
	private static Integer BROWSER_WINDOW_WIDTH;
	private static Integer BROWSER_WINDOW_HEIGHT;
	static LoadProperties prop= new LoadProperties();

	static {
		SELENIUM_HOST = prop.getProperty("driverhost");
		SELENIUM_PORT = prop.getProperty("driverport");
		PLATFORM = prop.getProperty("platform");
		BROWSER = prop.getProperty("browser");
		BROWSER_WINDOW_WIDTH = Integer.parseInt(prop.getProperty("browser.width"));
		BROWSER_WINDOW_HEIGHT = Integer.parseInt(prop.getProperty("browser.height"));
		BROWSER_WINDOW_SIZE = new Dimension(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);

		DRIVER_ROOT_DIR = prop.getProperty("driver.root.dir");
		SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
	}

	/**
	 * creates the browser driver specified in the system property "browser"
	 * if no property is set then a chrome browser driver is created.
	 * The allow properties are safari and chrome
	 * e.g to run with safari, pass in the option -Dbrowser=safari at runtime
	 *
	 * @return WebDriver
	 */
	public static WebDriver getBrowser() {
		Browsers browser;
		WebDriver dr;
		if (System.getProperty(BROWSER_PROP_KEY) == null) {
			browser = Browsers.CHROME;
		} else {
			browser = Browsers.browserForName(System.getProperty(BROWSER_PROP_KEY));
		}
		switch (browser) {
			case CHROME:
				dr = createChromeDriver();
				break;
			case FIREFOX:
				dr = createFireFoxDriver();
				break;
			case IE:
				dr = createIEDriver();
				break;
			case PHANTOMJS:
				dr = createPhantomJsDriver();
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
		if ("true".equalsIgnoreCase(isRemote)) {
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setVersion("32");
			capabilities.setPlatform(Platform.valueOf(PLATFORM));
			WebDriver remoteWebDriver = null;
			try {
				remoteWebDriver = getRemoteWebDriver(capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				LOG.info(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
			}
			return remoteWebDriver;
		}
		else {
			System.setProperty("webdriver.chrome.driver", DRIVER_ROOT_DIR);
			return new ChromeDriver();
		}


	}

	private static WebDriver createIEDriver() {
		//below code lets you switch between a local driver and the grid:
		String isRemote = System.getProperty("remote");
		if ("true".equalsIgnoreCase(isRemote)) {
		DesiredCapabilities capabilities = getInternetExploreDesiredCapabilities();
        WebDriver remoteWebDriver=null;
		try {
			remoteWebDriver= getRemoteWebDriver(capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			LOG.info(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
		}
		return remoteWebDriver;
		}
		else {
			System.setProperty("webdriver.ie.driver", DRIVER_ROOT_DIR);
			return new InternetExplorerDriver();
		}

	}
	private static WebDriver createFireFoxDriver() {
		//below code lets you switch between a local driver and the grid:
		String isRemote = System.getProperty("remote");
		if ("true".equalsIgnoreCase(isRemote)) {
		DesiredCapabilities capabilities = getFireFoxDesiredCapabilities();

		WebDriver remoteWebDriver = null;
		try {
			remoteWebDriver = getRemoteWebDriver(capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			LOG.info(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
		}
		return remoteWebDriver;
		}
		else {
			System.setProperty("webdriver.gecko.driver", DRIVER_ROOT_DIR);
			return new InternetExplorerDriver();
		}
	}



	private static WebDriver createPhantomJsDriver() {

		DesiredCapabilities capabilities = getPhantomJsCapabilities();
		WebDriver remoteWebDriver=null;
		try {
			remoteWebDriver= getRemoteWebDriver(capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			LOG.info(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
		}
		return remoteWebDriver;

	}





	private static DesiredCapabilities getFireFoxDesiredCapabilities() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setBrowserName("firefox");
		capabilities.setCapability("disable-restore-session-state", true);
		return capabilities;
	}



	private static DesiredCapabilities getInternetExploreDesiredCapabilities() {
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		capabilities.setVersion("9");
		return capabilities;

	}



	private static DesiredCapabilities getPhantomJsCapabilities() {
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, DRIVER_ROOT_DIR);
		return capabilities;
	}

	private static RemoteWebDriver getRemoteWebDriver(DesiredCapabilities capabilities) throws MalformedURLException {
		LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
		return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (capabilities));

	}
	
	private static void addAllBrowserSetup(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(dim);
		driver.manage().deleteAllCookies();
	}

}