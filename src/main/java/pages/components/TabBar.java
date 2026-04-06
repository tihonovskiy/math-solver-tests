package pages.components;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
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

    @Step("Open AI tab")
    public void openAi() {
        tap(aiTab);
    }

    @Step("Open Experts tab")
    public void openExperts() {
        tap(expertsTab);
    }

    @Step("Open History tab")
    public void openHistory() {
        tap(historyTab);
    }

    @Step("Open Settings tab")
    public void openSettings() {
        tap(settingsTab);
    }
}
