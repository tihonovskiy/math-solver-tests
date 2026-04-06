package pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import pages.popups.ExpertsPaywallPage;

public class ExpertsPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "Continue")
    private WebElement continueButton;

    @Step("Verify experts screen is loaded")
    public boolean isLoaded() {
        return isDisplayed(continueButton);
    }

    @Step("Tap 'Continue'")
    public ExpertsPaywallPage tapContinue() {
        tap(continueButton);
        return new ExpertsPaywallPage();
    }
}
