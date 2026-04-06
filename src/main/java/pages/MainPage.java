package pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import pages.components.TabBar;

public class MainPage extends BasePage {

    @iOSXCUITFindBy(accessibility = "Math Problems")
    private WebElement mathProblems;

    @iOSXCUITFindBy(accessibility = "Visual Problems")
    private WebElement visualProblems;

    @iOSXCUITFindBy(accessibility = "Graphic Calculator")
    private WebElement graphicCalculator;

    @iOSXCUITFindBy(accessibility = "Complex Science")
    private WebElement complexScience;

    private final TabBar tabBar = new TabBar();

    @Step("Verify main screen is loaded")
    public boolean isLoaded() {
        return isDisplayed(mathProblems);
    }

    @Step("Verify all 4 subject options are visible")
    public boolean areAllOptionsVisible() {
        return isDisplayed(mathProblems)
                && isDisplayed(visualProblems)
                && isDisplayed(graphicCalculator)
                && isDisplayed(complexScience);
    }

    @Step("Tap 'Math Problems'")
    public CameraPage tapMathProblems() {
        tap(mathProblems);
        return new CameraPage();
    }

    @Step("Tap 'Visual Problems'")
    public CameraPage tapVisualProblems() {
        tap(visualProblems);
        return new CameraPage();
    }

    @Step("Tap 'Graphic Calculator'")
    public CameraPage tapGraphicCalculator() {
        tap(graphicCalculator);
        return new CameraPage();
    }

    @Step("Tap 'Complex Science'")
    public CameraPage tapComplexScience() {
        tap(complexScience);
        return new CameraPage();
    }

    @Step("Open Experts tab")
    public ExpertsPage openExpertsTab() {
        tabBar.openExperts();
        return new ExpertsPage();
    }

    @Step("Open Settings tab")
    public SettingsPage openSettingsTab() {
        tabBar.openSettings();
        return new SettingsPage();
    }
}
