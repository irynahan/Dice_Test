package com.dice.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        this.browser = browser.toLowerCase();
        this.log = log;
    }

    // Create driver
    public WebDriver createDriver() {

        log.info("Create driver: " + browser);

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\projects\\Tools\\chrome-driver\\chromedriver.exe");
                driver.set(new ChromeDriver());
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\projects\\Tools\\geckodriver\\geckodriver.exe");
                driver.set(new FirefoxDriver());
                break;

            default:
                System.out.println("Do not know how to start: " + browser + ", starting chrome.");
                System.setProperty("webdriver.chrome.driver", "C:\\projects\\Tools\\chrome-driver\\chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
        }

        return driver.get();
    }

}
