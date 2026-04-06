package tests.paywalls;

import core.BaseTest;
import core.data.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.CameraPage;
import pages.SettingsPage;
import pages.popups.PaywallPage;

@Epic("Monetization")
@Feature("Main Paywall")
public class MainPaywallTest extends BaseTest {

    @Story("Cold start paywall")
    @Test(groups = {"smoke", "regression"},
          description = "Main paywall: shown on cold start with correct title, prices and free option pre-selected")
    public void mainPaywallOnColdStart() {
        SoftAssertions soft = new SoftAssertions();
        PaywallPage paywall = new PaywallPage();

        soft.assertThat(paywall.isDisplayed())
                .as("Paywall visible on cold start")
                .isTrue();
        soft.assertThat(paywall.getTitle())
                .as("Paywall title")
                .isEqualToNormalizingWhitespace(TestData.MAIN_PAYWALL_TITLE);
        soft.assertThat(paywall.getYearlyPrice())
                .as("Yearly price")
                .isEqualToNormalizingWhitespace(TestData.YEARLY_PRICE);
        soft.assertThat(paywall.getWeeklyPrice())
                .as("Weekly price")
                .isEqualToNormalizingWhitespace(TestData.WEEKLY_PRICE_MAIN);
        soft.assertThat(paywall.isFreeOptionActive())
                .as("Free trial (yearly) option is pre-selected by default")
                .isTrue();

        soft.assertAll();
    }

    @Story("Paywall from Settings")
    @Test(groups = {"smoke", "regression"},
          description = "Main paywall: shown from Settings → Try For Free with correct title and prices")
    public void mainPaywallFromSettings() {
        SoftAssertions soft = new SoftAssertions();

        SettingsPage settings = launchToMainScreen().openSettingsTab();
        PaywallPage paywall = settings.tapTryForFree();

        soft.assertThat(paywall.isDisplayed())
                .as("Paywall visible from Settings")
                .isTrue();
        soft.assertThat(paywall.getTitle())
                .as("Paywall title from Settings")
                .isEqualToNormalizingWhitespace(TestData.MAIN_PAYWALL_TITLE);
        soft.assertThat(paywall.getYearlyPrice())
                .as("Yearly price from Settings")
                .isEqualToNormalizingWhitespace(TestData.YEARLY_PRICE);
        soft.assertThat(paywall.getWeeklyPrice())
                .as("Weekly price from Settings")
                .isEqualToNormalizingWhitespace(TestData.WEEKLY_PRICE_MAIN);
        soft.assertThat(paywall.isFreeOptionActive())
                .as("Free trial option pre-selected")
                .isTrue();

        soft.assertAll();
    }

    @Story("Paywall from Camera")
    @Test(groups = {"smoke", "regression"},
          description = "Main paywall: shown after uploading an image and tapping Solve")
    public void mainPaywallFromCamera() {
        SoftAssertions soft = new SoftAssertions();

        CameraPage camera = launchToMainScreen().tapMathProblems();

        soft.assertThat(camera.isLoaded())
                .as("Camera screen opened")
                .isTrue();

        PaywallPage paywall = camera.uploadFirstImageFromLibrary();

        soft.assertThat(paywall.isDisplayed())
                .as("Paywall visible after image upload")
                .isTrue();
        soft.assertThat(paywall.getTitle())
                .as("Paywall title from camera")
                .isEqualToNormalizingWhitespace(TestData.MAIN_PAYWALL_TITLE);
        soft.assertThat(paywall.getYearlyPrice())
                .as("Yearly price from camera")
                .isEqualToNormalizingWhitespace(TestData.YEARLY_PRICE);
        soft.assertThat(paywall.getWeeklyPrice())
                .as("Weekly price from camera")
                .isEqualToNormalizingWhitespace(TestData.WEEKLY_PRICE_MAIN);

        soft.assertAll();
    }
}
