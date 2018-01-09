package com.williamssonoma.williamsSonomaPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class WilliamsSonomaShoppingCartPage extends BaseTestPage{
	
	public WilliamsSonomaShoppingCartPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//h1[text()='Shopping Cart']")
	public WebElement headerShoppingCart;

	@FindBy(xpath ="//h2[@class='delivery-method'][normalize-space()='Pick Up In Store Stanford Shopping Center']")
	public WebElement headerDeliveryMethod;

	@FindBy(xpath="//div[@class='cart-table']/div[@class='cart-table-row']")
	public List<WebElement> shoppingCartTableRows;

	@FindBy(xpath="//div[@class='cart-table-row-title']")
	public WebElement headerCartTableRowTitle;

	public List<String> getProductsListFromShoppingCartTable(){
		List<String> productNamesList=null;
		for (WebElement row : shoppingCartTableRows)
		{
			String productName= row.findElement(By.className("cart-table-row-title")).getText();
			productNamesList.add(productName);
		}
		System.out.println("Product List in Shopping Cart: "+productNamesList);
		return productNamesList;
	}



}
