package pages;

import core.utils.Waits;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.popups.PaywallPage;

import java.util.Map;

public class CameraPage extends BasePage {

    // Main camera screen
    @iOSXCUITFindBy(accessibility = "Take a photo of the task")
    private WebElement hintLabel;

    @iOSXCUITFindBy(accessibility = "ic library")
    private WebElement libraryButton;

    // Native back button (accessibility id confirmed via Inspector).
    @iOSXCUITFindBy(accessibility = "ic back")
    private WebElement backButton;

    // Crop screen — becomes clickable once the animation finishes.
    @iOSXCUITFindBy(accessibility = "Solve")
    private WebElement solveButton;

    public boolean isLoaded() {
        return isDisplayed(hintLabel);
    }

    /**
     * Opens the photo picker, selects the most recent photo from the library,
     * waits for the crop animation to finish, taps Solve and returns the paywall.
     */
    public PaywallPage uploadFirstImageFromLibrary() {
        tap(libraryButton);

        // Wait for the photo grid to appear in the XCUITest hierarchy.
        Waits.defaultWait(driver).until(
                ExpectedConditions.presenceOfElementLocated(
                        AppiumBy.iOSNsPredicateString(
                                "type == 'XCUIElementTypeImage' AND name == 'PXGGridLayout-Info'")));

        WebElement newestPhoto = driver.findElements(
                        AppiumBy.iOSNsPredicateString(
                                "type == 'XCUIElementTypeImage' AND name == 'PXGGridLayout-Info'"))
                .getFirst();
        Rectangle rect = newestPhoto.getRect();
        int centerX = rect.getX() + rect.getWidth() / 2;
        int centerY = rect.getY() + rect.getHeight() / 2;
        driver.executeScript("mobile: tap", Map.of("x", centerX, "y", centerY));

        // Waits until the crop animation finishes and Solve becomes interactive.
        tap(solveButton);

        return new PaywallPage();
    }

    /**
     * Taps the native back button to return to the main screen.
     */
    public MainPage goBack() {
        tap(backButton);
        return new MainPage();
    }
}
