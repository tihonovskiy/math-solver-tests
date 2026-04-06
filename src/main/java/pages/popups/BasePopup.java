package pages.popups;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.MainPage;

public abstract class BasePopup extends BasePage {

    @iOSXCUITFindBy(accessibility = "cross")
    private WebElement closeButton;

    public abstract boolean isDisplayed();

    public MainPage close() {
        tap(closeButton);
        return new MainPage();
    }
}
