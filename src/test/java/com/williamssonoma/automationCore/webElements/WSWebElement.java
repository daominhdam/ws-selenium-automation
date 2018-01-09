package com.williamssonoma.automationCore.webElements;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.internal.Locatable;

import org.openqa.selenium.internal.WrapsElement;



public interface WSWebElement extends WebElement, WrapsElement, Locatable {

    /**

     * Returns true when the inner element is ready to be used.

     *

     * @return boolean true for an initialized WebElement, or false if we were somehow passed a null WebElement.

     */

    boolean elementWired();

    

    String getValue();

        

    public boolean hasClass(String className);



	boolean hasAttribute(String attribute);

        

    WebDriver getElementDriver();



    boolean isEditable();



}