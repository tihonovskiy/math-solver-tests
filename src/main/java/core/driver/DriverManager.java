package core.driver;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DriverManager {

    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();

    // Today only iOS; swap/select implementation here when Android is added.
    private static final PlatformDriverFactory FACTORY = new IOSDriverFactory();

    private DriverManager() {
    }

    public static void initDriver() {
        if (DRIVER.get() != null) {
            throw new IllegalStateException(
                    "Driver already initialized for thread " + Thread.currentThread().getName()
                            + ". Call quitDriver() before re-initializing.");
        }
        DRIVER.set(FACTORY.create());
    }

    public static AppiumDriver getDriver() {
        AppiumDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "Driver not initialized for thread " + Thread.currentThread().getName()
                            + ". Did you forget to extend BaseTest or call initDriver()?");
        }
        return driver;
    }

    public static void quitDriver() {
        AppiumDriver driver = DRIVER.get();
        if (driver == null) return;
        try {
            driver.quit();
        } catch (Exception e) {
            log.warn("Error while quitting driver: {}", e.getMessage());
        } finally {
            DRIVER.remove();
        }
    }
}
