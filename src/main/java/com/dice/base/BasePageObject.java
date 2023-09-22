package com.dice.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePageObject <T> {

    protected WebDriver driver;
    protected Logger log;


    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    protected T getPage(String url) {
        driver.get(url);
        return (T) this;
    }

    private WebElement find(By element) {
        return driver.findElement(element);
    }

    protected void type(String text, By element){
        find(element).sendKeys(text);
    }


    protected void click(By element){
        find(element).click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    protected String getElementsText (By element) {
       return find(element).getText();
    }


}
