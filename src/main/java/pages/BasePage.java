package pages;

import core.driver.DriverManager;
import core.utils.Waits;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public abstract class BasePage {

    protected final AppiumDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void tap(WebElement element) {
        Waits.clickable(driver, element).click();
    }

    protected void type(WebElement element, String text) {
        WebElement visible = Waits.visible(driver, element);
        visible.clear();
        visible.sendKeys(text);
    }

    protected String readText(WebElement element) {
        return Waits.visible(driver, element).getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return Waits.visibleQuick(driver, element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void scrollTo(WebElement element) {
        String elementId = ((RemoteWebElement) element).getId();
        driver.executeScript("mobile: scrollToElement", Map.of("elementId", elementId));
    }
}
