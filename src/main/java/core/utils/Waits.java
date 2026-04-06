package core.utils;

import core.config.ConfigProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class Waits {

    private static final Duration DEFAULT_TIMEOUT =
            Duration.ofSeconds(ConfigProvider.get().waitTimeoutSeconds());

    private static final Duration QUICK_TIMEOUT = Duration.ofSeconds(3);

    private Waits() {
    }

    public static WebDriverWait defaultWait(WebDriver driver) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public static WebElement visible(WebDriver driver, WebElement element) {
        return defaultWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement clickable(WebDriver driver, WebElement element) {
        return defaultWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement visibleQuick(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, QUICK_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }
}
