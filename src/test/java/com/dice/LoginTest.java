package com.dice;

import com.dice.base.BaseTest;
import com.dice.base.CsvDataProviders;
import com.dice.pages.LoginPage;
import com.dice.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginTest extends BaseTest {

    @Test ()
    public void positiveLoginTest() {

        LoginPage loginPage = new LoginPage(driver, log);

        String expectedPageTitle = "Dashboard Home Feed | Dice.com";
        String correctProfileName = "Shyshkin";

        // open dice log in page
        loginPage.openLogInPage();

        // fill in login and password
        loginPage.fillInEmailAndPassword("dmitrysqa@gmail.com", "Automation2017");

        // push Sign In button
        ProfilePage profilePage = loginPage.pushSignInButton();


        // Verifications
        // - verify title of the page
        String actualTitle = profilePage.getTitle();
        Assert.assertEquals(actualTitle, expectedPageTitle, "Page title is not expected. \nExpected: " + expectedPageTitle + "\nActual: " + actualTitle );

        // - verify name on profile page
        Assert.assertTrue(profilePage.isCorrectProfileLoaded(correctProfileName), "Profile name is not correct");
    }

    @Test (dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLoginTest( Map<String, String> testData) {

        LoginPage loginPage = new LoginPage(driver, log);

        String testNumber = testData.get("no");
        String email = testData.get("email");
        String password = testData.get("password");
        String description = testData.get("description");
        String expectedErrorMessage = "Email and/or password incorrect." ;

        log.info("Test No #" + testNumber + "for" + description + " Where\nEmail: " + email + "\nPassword: " + password);

        // open dice log in page
        loginPage.openLogInPage();

        // fill in login and password
        loginPage.fillInEmailAndPassword(email, password);

        // push Sign In button
        loginPage.pushSignInButton();

        // get actual error message
        String actualErrorMessage = loginPage.getLoginErrorMessage();

        // Verifications
        // Verify error message

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Error message is not expected. \nExpected error message is: " + expectedErrorMessage + "\nActual error message is: " + actualErrorMessage);

    }
}
