package core.listeners;

import core.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class ScreenshotListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(ScreenshotListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot(result, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        attachScreenshot(result, "SKIPPED");
    }

    private void attachScreenshot(ITestResult result, String status) {
        AppiumDriver driver;
        try {
            driver = DriverManager.getDriver();
        } catch (IllegalStateException e) {
            log.warn("Driver not available for screenshot ({})", result.getName());
            return;
        }

        try {
            byte[] png = driver.getScreenshotAs(OutputType.BYTES);
            String label = "[" + status + "] " + result.getName();
            Allure.addAttachment(label, "image/png", new ByteArrayInputStream(png), "png");
            log.info("Screenshot attached to Allure report for: {}", result.getName());
        } catch (Exception e) {
            log.error("Failed to capture screenshot for '{}': {}", result.getName(), e.getMessage());
        }
    }
}
