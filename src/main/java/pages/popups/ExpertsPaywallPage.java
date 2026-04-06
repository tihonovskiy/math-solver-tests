package pages.popups;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ExpertsPaywallPage extends BasePopup {

    @iOSXCUITFindBy(accessibility = "Unlock experts solutions")
    private WebElement title;

    // Predicate-based so the locator stays valid regardless of the exact price value.
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND name CONTAINS 'USD per week'")
    private WebElement weeklyPrice;

    @Override
    public boolean isDisplayed() {
        return isDisplayed(title);
    }

    public String getTitle() {
        return readText(title);
    }

    public String getWeeklyPrice() {
        return readText(weeklyPrice);
    }
}
