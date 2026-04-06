package pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
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
    public boolean isLoaded() {
        return isDisplayed(screenTitle);
    }

    public PaywallPage tapTryForFree() {
        tap(tryForFreeButton);
        return new PaywallPage();
    }

    public ExpertsPaywallPage tapGetNow() {
        tap(getNowButton);
        return new ExpertsPaywallPage();
    }

    public boolean isTermsOfUseVisible()      { return isDisplayed(termsOfUseButton); }
    public boolean isPrivacySecurityVisible() { return isDisplayed(privacySecurityButton); }
    public boolean isSoundsHapticsVisible()   { return isDisplayed(soundsHapticsLabel); }
    public boolean isLanguageForAIVisible()   { return isDisplayed(languageButton); }
    public boolean isContactUsVisible()       { return isDisplayed(contactUsButton); }
    public boolean isShareIdeasVisible()      { scrollTo(shareIdeasButton); return isDisplayed(shareIdeasButton); }
    public boolean isUserIdVisible()          { scrollTo(userIdButton);    return isDisplayed(userIdButton); }
}
