package com.williamssonoma.automationCore.webElements.Impl;

import com.williamssonoma.automationCore.webElements.WSWebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsDriver;

import java.util.Arrays;
import java.util.List;

public class WSWebElementImpl implements WSWebElement {
    private final WebElement element;



    /**

     * Creates a Element for a given WebElement.

     *

     * @param element element to wrap up

     */

    public WSWebElementImpl(final WebElement element) {

        this.element = element;

    }



    //@Override

    public void click() {

        element.click();

    }



    // @Override

    public void sendKeys(CharSequence... keysToSend) {

        element.sendKeys(keysToSend);

    }



    //@Override

    public Point getLocation() {

        return element.getLocation();

    }



    //@Override

    public void submit() {

        element.submit();

    }



    //@Override

    public String getAttribute(String name) {

        return element.getAttribute(name);

    }



    //@Override

    public String getCssValue(String propertyName) {

        return element.getCssValue(propertyName);

    }



    //@Override

    public Dimension getSize() {

        return element.getSize();

    }

    @Override
    public Rectangle getRect() {
        return null;
    }


    //@Override

    public List<WebElement> findElements(By by) {

        return element.findElements(by);

    }



    //@Override

    public String getText() {

        return element.getText();

    }



    //@Override

    public String getTagName() {

        return element.getTagName();

    }



    //@Override

    public boolean isSelected() {

        return element.isSelected();

    }



    //@Override

    public WebElement findElement(By by) {

        return element.findElement(by);

    }



    //@Override

    public boolean isEnabled() {

        return element.isEnabled();

    }



    //@Override

    public boolean isDisplayed() {

        return element.isDisplayed();

    }



    //@Override

    public void clear() {

        throw new NoSuchMethodError("Method clear is not available at element level.");

    }



    //@Override

    public WebElement getWrappedElement() {

        return element;

    }



    //@Override

    @SuppressWarnings("deprecation")

    public Point getLocationOnScreenOnceScrolledIntoView() {

        return ((WSWebElementImpl) element).getLocationOnScreenOnceScrolledIntoView();

    }



    //@Override

    public Coordinates getCoordinates() {

        return ((Locatable) element).getCoordinates();

    }



    //@Override

    public boolean elementWired() {

        return (element != null);

    }



	/*

	public void jsClick() {

	}



	public void jsMouseOver(){

	}

	*/



    public WebDriver getElementDriver(){

        WrapsDriver wrapsDriver = (WrapsDriver) element;

        return wrapsDriver.getWrappedDriver();

    }



    protected String getID(){

        return element.getAttribute("id");

    }



    @Override

    public String getValue() {

        return element.getAttribute("value");

    }



    public boolean hasClass(String className){

        return Arrays.asList(getWrappedElement().getAttribute("class").split(" ")).contains(className);

    }



    public boolean hasAttribute(String attribute){

        String jsScript = "return document.getElementById(arguments[0]).hasAttribute(arguments[1]);";

        JavascriptExecutor js = (JavascriptExecutor) getElementDriver();

        Object response = js.executeScript(jsScript, getID(), attribute);

        return (boolean) response;

    }



    public boolean isEditable(){

        if (!hasAttribute("readonly")){

            return true;

        }

        return false;

    }


    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }
}


