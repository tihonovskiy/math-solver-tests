package tests;

import core.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.SettingsPage;

@Epic("Settings")
@Feature("Settings Screen")
public class SettingsTest extends BaseTest {

    @Story("Settings options visibility")
    @Test(groups = "regression",
          description = "Settings: all required options are visible")
    public void settingsOptionsSmoke() {
        SoftAssertions soft = new SoftAssertions();

        SettingsPage settings = launchToMainScreen().openSettingsTab();

        soft.assertThat(settings.isLoaded())
                .as("Settings screen loaded")
                .isTrue();
        soft.assertThat(settings.isTermsOfUseVisible())
                .as("Terms of Use is visible")
                .isTrue();
        soft.assertThat(settings.isPrivacySecurityVisible())
                .as("Privacy & Security is visible")
                .isTrue();
        soft.assertThat(settings.isSoundsHapticsVisible())
                .as("Sounds & Haptics is visible")
                .isTrue();
        soft.assertThat(settings.isLanguageForAIVisible())
                .as("Language for AI Answers is visible")
                .isTrue();
        soft.assertThat(settings.isContactUsVisible())
                .as("Contact Us is visible")
                .isTrue();
        soft.assertThat(settings.isShareIdeasVisible())
                .as("Share Your Ideas with Us is visible")
                .isTrue();
        soft.assertThat(settings.isUserIdVisible())
                .as("User ID is visible")
                .isTrue();

        soft.assertAll();
    }
}
