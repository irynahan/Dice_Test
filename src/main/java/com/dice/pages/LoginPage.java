package com.dice.pages;

import com.dice.base.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject <LoginPage> {

    private static final String URL = "https://www.dice.com/dashboard/login";

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By emailField = By.id("email");
    private By passwordField = By.id("password") ;
    private By signInButton = By.xpath("//button[@type = 'submit']");

    public void openLogInPage() {
        getPage(URL);
    }

    public void fillInEmailAndPassword(String email, String password){
        type(email, emailField);
        type(password, passwordField);
    }

    public ProfilePage pushSignInButton() {
        click(signInButton);
        return new ProfilePage(driver, log);
    }

}
