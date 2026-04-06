package pages.popups;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.MainPage;

public abstract class BasePopup extends BasePage {

    @iOSXCUITFindBy(accessibility = "cross")
    private WebElement closeButton;

    public abstract boolean isDisplayed();

    @Step("Close popup")
    public MainPage close() {
        tap(closeButton);
        return new MainPage();
    }
}
