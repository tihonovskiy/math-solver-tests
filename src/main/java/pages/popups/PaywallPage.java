package pages.popups;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class PaywallPage extends BasePopup {

    @iOSXCUITFindBy(accessibility = "Get All Features for Free")
    private WebElement title;

    // Predicate-based so the locator stays valid regardless of the exact price value.
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS 'annual payment'")
    private WebElement yearlyPrice;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS 'USD/week'")
    private WebElement weeklyPrice;

    @iOSXCUITFindBy(accessibility = "Try For Free")
    private WebElement tryForFreeButton;

    // Present when the yearly plan (free trial) is selected by default.
    @iOSXCUITFindBy(accessibility = "3-day free trial")
    private WebElement freeTrialLabel;

    @Step("Verify main paywall is visible")
    @Override
    public boolean isDisplayed() {
        return isDisplayed(tryForFreeButton);
    }

    @Step("Read paywall title")
    public String getTitle() {
        return readText(title);
    }

    @Step("Read yearly price")
    public String getYearlyPrice() {
        return readText(yearlyPrice);
    }

    @Step("Read weekly price")
    public String getWeeklyPrice() {
        return readText(weeklyPrice);
    }

    /** Returns true when the yearly plan with free trial is pre-selected. */
    @Step("Check free trial option is active")
    public boolean isFreeOptionActive() {
        return isDisplayed(freeTrialLabel);
    }
}
