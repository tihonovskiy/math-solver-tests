package pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import pages.popups.ExpertsPaywallPage;
import pages.popups.PaywallPage;

public class SettingsPage extends BasePage {

    // Stable anchor — NavigationBar title is always present on this screen.
    @iOSXCUITFindBy(accessibility = "Settings")
    private WebElement screenTitle;

    // Premium banner
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name CONTAINS 'Try For Free'")
    private WebElement tryForFreeButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name CONTAINS 'Get Now'")
    private WebElement getNowButton;

    // Settings options
    @iOSXCUITFindBy(accessibility = "Terms of Use")
    private WebElement termsOfUseButton;

    @iOSXCUITFindBy(accessibility = "Privacy & Security")
    private WebElement privacySecurityButton;

    @iOSXCUITFindBy(accessibility = "Sounds & Haptics")
    private WebElement soundsHapticsLabel;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name BEGINSWITH 'Language for AI Answers'")
    private WebElement languageButton;

    @iOSXCUITFindBy(accessibility = "Contact Us")
    private WebElement contactUsButton;

    @iOSXCUITFindBy(accessibility = "Share Your Ideas with Us")
    private WebElement shareIdeasButton;

    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name BEGINSWITH 'User ID'")
    private WebElement userIdButton;

    /** Uses the NavigationBar title as a stable anchor. */
    @Step("Verify settings screen is loaded")
    public boolean isLoaded() {
        return isDisplayed(screenTitle);
    }

    @Step("Tap 'Try For Free'")
    public PaywallPage tapTryForFree() {
        tap(tryForFreeButton);
        return new PaywallPage();
    }

    @Step("Tap 'Get Now'")
    public ExpertsPaywallPage tapGetNow() {
        tap(getNowButton);
        return new ExpertsPaywallPage();
    }

    @Step("Check Terms of Use is visible")
    public boolean isTermsOfUseVisible()      { return isDisplayed(termsOfUseButton); }

    @Step("Check Privacy & Security is visible")
    public boolean isPrivacySecurityVisible() { return isDisplayed(privacySecurityButton); }

    @Step("Check Sounds & Haptics is visible")
    public boolean isSoundsHapticsVisible()   { return isDisplayed(soundsHapticsLabel); }

    @Step("Check Language for AI Answers is visible")
    public boolean isLanguageForAIVisible()   { return isDisplayed(languageButton); }

    @Step("Check Contact Us is visible")
    public boolean isContactUsVisible()       { return isDisplayed(contactUsButton); }

    @Step("Check Share Your Ideas is visible")
    public boolean isShareIdeasVisible()      { scrollTo(shareIdeasButton); return isDisplayed(shareIdeasButton); }

    @Step("Check User ID is visible")
    public boolean isUserIdVisible()          { scrollTo(userIdButton); return isDisplayed(userIdButton); }
}
