package pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
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

    public boolean isLoaded() {
        return isDisplayed(mathProblems);
    }

    public boolean areAllOptionsVisible() {
        return isDisplayed(mathProblems)
                && isDisplayed(visualProblems)
                && isDisplayed(graphicCalculator)
                && isDisplayed(complexScience);
    }

    public CameraPage tapMathProblems() {
        tap(mathProblems);
        return new CameraPage();
    }

    public CameraPage tapVisualProblems() {
        tap(visualProblems);
        return new CameraPage();
    }

    public CameraPage tapGraphicCalculator() {
        tap(graphicCalculator);
        return new CameraPage();
    }

    public CameraPage tapComplexScience() {
        tap(complexScience);
        return new CameraPage();
    }

    public ExpertsPage openExpertsTab() {
        tabBar.openExperts();
        return new ExpertsPage();
    }

    public SettingsPage openSettingsTab() {
        tabBar.openSettings();
        return new SettingsPage();
    }
}
