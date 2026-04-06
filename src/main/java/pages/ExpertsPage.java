package pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import pages.popups.ExpertsPaywallPage;

public class ExpertsPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "Continue")
    private WebElement continueButton;

    public boolean isLoaded() {
        return isDisplayed(continueButton);
    }

    public ExpertsPaywallPage tapContinue() {
        tap(continueButton);
        return new ExpertsPaywallPage();
    }
}
