package com.dice.pages;

import com.dice.base.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePageObject <ProfilePage> {

    public ProfilePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By lastName = By.id("contact-last-name");

    public boolean isCorrectProfileLoaded(String correctProfileName) {
        if (getElementsText(lastName).equals(correctProfileName)){
            return true;
        }
        return false;

    }
}
