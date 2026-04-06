package core.driver;

import io.appium.java_client.AppiumDriver;

/**
 * Open/Closed: adding Android later means a new implementation, no changes here.
 */
public interface PlatformDriverFactory {
    AppiumDriver create();
}
