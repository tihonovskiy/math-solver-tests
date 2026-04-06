package tests;

import core.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.CameraPage;
import pages.MainPage;

public class MainScreenTest extends BaseTest {

    @Test(groups = {"smoke", "regression"},
          description = "Main screen: all 4 options visible and each opens the camera")
    public void mainScreenOptionsSmoke() {
        SoftAssertions soft = new SoftAssertions();

        MainPage main = launchToMainScreen();

        soft.assertThat(main.isLoaded())
                .as("Main screen loaded")
                .isTrue();
        soft.assertThat(main.areAllOptionsVisible())
                .as("All 4 subject options are visible")
                .isTrue();

        // Math Problems → camera
        CameraPage camera = main.tapMathProblems();
        soft.assertThat(camera.isLoaded())
                .as("Camera opens for Math Problems")
                .isTrue();

        // Visual Problems → camera
        camera = camera.goBack().tapVisualProblems();
        soft.assertThat(camera.isLoaded())
                .as("Camera opens for Visual Problems")
                .isTrue();

        // Graphic Calculator → camera
        camera = camera.goBack().tapGraphicCalculator();
        soft.assertThat(camera.isLoaded())
                .as("Camera opens for Graphic Calculator")
                .isTrue();

        // Complex Science → camera
        camera = camera.goBack().tapComplexScience();
        soft.assertThat(camera.isLoaded())
                .as("Camera opens for Complex Science")
                .isTrue();

        soft.assertAll();
    }
}
