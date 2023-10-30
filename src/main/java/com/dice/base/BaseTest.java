package com.dice.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, @Optional("chrome") String browser, ITestContext ctx) {

        // ctx - context is used to get current test name for log acc. Xml file;
        String testName = ctx.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        //
        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterMethod(enabled = true, alwaysRun = true)
    public void tearDown() {
        // close browser
        log.info("Close driver");
        driver.quit();

    }

}
