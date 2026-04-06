package pages.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class TabBar extends BasePage {

    @iOSXCUITFindBy(accessibility = "AI")
    private WebElement aiTab;

    @iOSXCUITFindBy(accessibility = "Experts")
    private WebElement expertsTab;

    @iOSXCUITFindBy(accessibility = "History")
    private WebElement historyTab;

    @iOSXCUITFindBy(accessibility = "Settings")
    private WebElement settingsTab;

    public void openAi() {
        tap(aiTab);
    }

    public void openExperts() {
        tap(expertsTab);
    }

    public void openHistory() {
        tap(historyTab);
    }

    public void openSettings() {
        tap(settingsTab);
    }
}
