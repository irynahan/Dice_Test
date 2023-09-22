import com.dice.base.BaseTest;
import com.dice.pages.LoginPage;
import com.dice.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
