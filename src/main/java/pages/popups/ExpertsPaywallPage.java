package pages.popups;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class ExpertsPaywallPage extends BasePopup {

    @iOSXCUITFindBy(accessibility = "Unlock experts solutions")
    private WebElement title;

    // Predicate-based so the locator stays valid regardless of the exact price value.
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS 'USD per week'")
    private WebElement weeklyPrice;

    @Step("Verify experts paywall is visible")
    @Override
    public boolean isDisplayed() {
        return isDisplayed(title);
    }

    @Step("Read experts paywall title")
    public String getTitle() {
        return readText(title);
    }

    @Step("Read weekly price")
    public String getWeeklyPrice() {
        return readText(weeklyPrice);
    }
}
