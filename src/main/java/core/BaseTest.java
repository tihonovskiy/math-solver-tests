package core;

import core.driver.DriverManager;
import core.listeners.ScreenshotListener;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.MainPage;
import pages.popups.PaywallPage;

@Listeners(ScreenshotListener.class)
public abstract class BaseTest {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected MainPage launchToMainScreen() {
        return new PaywallPage().close();
    }

    // public so ScreenshotListener can access the driver without reflection.
    public AppiumDriver getDriver() {
        return DriverManager.getDriver();
    }
}
