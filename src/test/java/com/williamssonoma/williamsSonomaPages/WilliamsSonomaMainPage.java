package com.williamssonoma.williamsSonomaPages;

import Logger.Log;
import com.williamssonoma.automationCore.util.LoadProperties;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents.PopupOverlayJoinEmailListWidgetComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WilliamsSonomaMainPage extends BaseTestPage{
	public WilliamsSonomaMainPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//section[@id='sub-brand-bar-container']")
	List<WebElement> menuSubBrandBarContainer;

	@FindBy(xpath="//div[@id='brand-logo']/a/img[@alt='Williams Sonoma']")
	List<WebElement> imageWSBrandLogo;


	@FindBy(xpath="//nav[@id='topnav-container']")
	List<WebElement> menuBarAllProduct;

	@FindBy(xpath="//nav[@id='topnav-container']/ul[@class='nav-menu']/li")
	List<WebElement> linkAllProducts;

	@FindBy(xpath="//a[@class='topnav-cookware ']")
	public WebElement linkCookware;

	@FindBy(xpath="//div[contains(@class, 'stickyOverlayWidget')]")
	public WebElement popupOverlayWidget;

	@FindBy(xpath="//a[contains(@class, 'stickyOverlayMinimizeButton')]")
	public WebElement buttonStickyOverlayMinimize ;

	public PopupOverlayJoinEmailListWidgetComponent popupOverlayJoinEmailListWidgetComponent;

	public void clickProductLinkFromMenu(String productName){
		for (WebElement link : linkAllProducts){

			WebElement productLink= link.findElement(By.tagName("a"));
			if (productLink.getText().trim().equals(productName))
			{
				try {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);
					waitForElementToBeVisible(By.tagName("a"));
					if (productLink.isDisplayed())
						productLink.click(); // click the desired option
				}catch(WebDriverException we){
					String url=productLink.getAttribute("href");
					System.out.println("Navigating to: "+url );
					driver.navigate().to(url);
				}catch(Exception e){
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public void launchPage() {
		LoadProperties prop = new LoadProperties();
		String baseurl = prop.getProperty("baseurl");
		driver.get(baseurl);
		waitForPageToLoad();
		WilliamsSonomaMainPage williamsSonomaMainPage = new WilliamsSonomaMainPage(driver);
		williamsSonomaMainPage.waitForPageToLoad();
		try {
			if (williamsSonomaMainPage.popupOverlayWidget.isDisplayed()) {
				Log.info("Closing the 'JoinEmailList' popup overlay widget");
				williamsSonomaMainPage.buttonStickyOverlayMinimize.click();
			}
			driver.manage().window().maximize();
		} catch (Exception e) {
			Log.info("The 'JoinEmailList' popup overlay widget is not displayed ");
		}
	}

	public void waitForPageToLoad(){
		waitForElementToBeVisible(By.id("topnav-container"));
	}



	}
