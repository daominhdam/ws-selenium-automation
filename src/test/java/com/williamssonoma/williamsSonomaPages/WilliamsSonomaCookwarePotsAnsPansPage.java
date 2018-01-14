package com.williamssonoma.williamsSonomaPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class WilliamsSonomaCookwarePotsAnsPansPage extends BaseTestPage{
	
	public WilliamsSonomaCookwarePotsAnsPansPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}



	@FindBy(xpath="//h1[text()='Cookware']")
	public WebElement headerCookware;

	@FindBy(xpath="//div[@class='left-nav-cat-heading'][text()='Featured']")
	public WebElement menuFeatured;

	@FindBy(xpath="//div[@class='left-nav-cat-heading'][text()='Shop by Category']")
	public WebElement menuShopByCategory;

	@FindBy(xpath="//div[@class='left-nav-cat-heading'][text()='Shop by Category']/following-sibling::ul/li[@class='navLink ']")
	public List<WebElement> linksAllProductsShopByCategory;

	@FindBy(xpath="//div[contains(@class, 'stickyOverlayWidget')]")
	public WebElement popupOverlayWidget;

	@FindBy(xpath="//a[contains(@class, 'stickyOverlayMinimizeButton')]")
	public WebElement buttonStickyOverlayMinimize ;


	public By menuShopByCategoryLocator = By.xpath("//div[@class='left-nav-cat-heading'][text()='Shop by Category']");

	public void clickMenuShopByCategory(String productName){
		for (WebElement link : linksAllProductsShopByCategory)
		{
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

	public void waitForPageToLoad(){
		waitForElementToBeVisible(By.xpath("//h1[text()='Cookware']"));
		waitForElementToBeVisible(By.xpath("//div[@class='left-nav-cat-heading'][text()='Shop by Category']"));
	}






}
