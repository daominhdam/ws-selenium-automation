package com.williamssonoma.williamsSonomaPages;

import Logger.Log;
import com.williamssonoma.automationCore.util.LoadProperties;
import com.williamssonoma.williamsSonomaPages.WilliamsSonomaCommonPageComponents.PopupOverlayJoinEmailListWidgetComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	@FindBy(xpath="//ul[@class='nav-menu']/li")
	List<WebElement> linkAllProducts;

	@FindBy(xpath="//a[@class='topnav-cookware ']")
	public WebElement linkCookware;

	@FindBy(xpath="//div[contains(@class, 'stickyOverlayWidget')]")
	public WebElement popupOverlayWidget;

	@FindBy(xpath="//a[contains(@class, 'stickyOverlayMinimizeButton')]")
	public WebElement buttonStickyOverlayMinimize ;

	public PopupOverlayJoinEmailListWidgetComponent popupOverlayJoinEmailListWidgetComponent;

	public void clickProductLinkFromMenu(String productName){
		for (WebElement product : linkAllProducts)
		{
			if (product.getText().trim().equals(productName))
			{
				product.click(); // click the desired option
				break;
			}
		}
	}

	public void launchPage() {
		LoadProperties prop = new LoadProperties();
		String baseurl= prop.getProperty("baseurl");
		driver.get(baseurl);
		waitForPageToLoad();
		WilliamsSonomaMainPage williamsSonomaMainPage= new WilliamsSonomaMainPage(driver);
		williamsSonomaMainPage.waitForPageToLoad();
		if (williamsSonomaMainPage.popupOverlayWidget.isDisplayed()) {
			Log.info("Closing the 'JoinEmailList' popup overlay widget");
			williamsSonomaMainPage.buttonStickyOverlayMinimize.click();
		}
		driver.manage().window().maximize();
	}

	public void waitForPageToLoad(){
		waitForElementToBeVisible(By.id("topnav-container"));
	}



	}
