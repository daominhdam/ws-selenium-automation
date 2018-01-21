package com.williamssonoma.williamsSonomaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class WilliamsSonomaTeaKettlesPage extends BaseTestPage{
	
	public WilliamsSonomaTeaKettlesPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//h1[text()='Tea Kettles']")
	public WebElement headerTeaKettles;

	public By headerTeaKettlesLocator= By.xpath("//h1[text()='Tea Kettles']");

	@FindBy(xpath="//div[@id='subCatListContainer']/ul[@data-subcategory='Tea Kettles']/li")
	public List<WebElement> linksAllTeaKettles;

	public List<String> getAllProductNames(){
		List<String> productNames=new ArrayList<String>();
		for (WebElement link : linksAllTeaKettles)
		{
			WebElement productLink= link.findElement(By.xpath("//a[@class='product-name']"));
			String productName= productLink.getText();
			productNames.add(productName);
		}
		return productNames;
	}

	public void clickProductLink(String productName){
		for (WebElement link : linksAllTeaKettles)
		{
			WebElement productLink= link.findElement(By.tagName("//a[@class='product-name']"));
			if (productLink.getText().trim().equals(productName))
			{
				productLink.click(); // click the desired option
				break;
			}
		}
	}

}
