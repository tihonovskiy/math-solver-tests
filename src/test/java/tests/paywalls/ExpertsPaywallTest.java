package tests.paywalls;

import core.BaseTest;
import core.data.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.popups.ExpertsPaywallPage;

@Epic("Monetization")
@Feature("Experts Paywall")
public class ExpertsPaywallTest extends BaseTest {

    @Story("Paywall from Experts tab")
    @Test(groups = "regression",
          description = "Experts paywall: shown from Experts tab → Continue with correct title and price")
    public void expertsPaywallFromExpertsTab() {
        SoftAssertions soft = new SoftAssertions();

        ExpertsPaywallPage paywall = launchToMainScreen()
                .openExpertsTab()
                .tapContinue();

        soft.assertThat(paywall.isDisplayed())
                .as("Experts paywall is visible")
                .isTrue();
        soft.assertThat(paywall.getTitle())
                .as("Experts paywall title")
                .isEqualToNormalizingWhitespace(TestData.EXPERTS_PAYWALL_TITLE);
        soft.assertThat(paywall.getWeeklyPrice())
                .as("Weekly price")
                .isEqualToNormalizingWhitespace(TestData.WEEKLY_PRICE_EXPERTS);

        soft.assertAll();
    }

    @Story("Paywall from Settings")
    @Test(groups = "regression",
          description = "Experts paywall: shown from Settings → Get Now with correct title and price")
    public void expertsPaywallFromSettings() {
        SoftAssertions soft = new SoftAssertions();

        ExpertsPaywallPage paywall = launchToMainScreen()
                .openSettingsTab()
                .tapGetNow();

        soft.assertThat(paywall.isDisplayed())
                .as("Experts paywall is visible from Settings")
                .isTrue();
        soft.assertThat(paywall.getTitle())
                .as("Experts paywall title from Settings")
                .isEqualToNormalizingWhitespace(TestData.EXPERTS_PAYWALL_TITLE);
        soft.assertThat(paywall.getWeeklyPrice())
                .as("Weekly price from Settings")
                .isEqualToNormalizingWhitespace(TestData.WEEKLY_PRICE_EXPERTS);

        soft.assertAll();
    }
}
