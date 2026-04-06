# TODO — Planned Improvements

Backlog of known improvements, ordered roughly by priority.

---

## Known Technical Debt

### TD-1: Resolve `ContextAware` compatibility shim
`src/main/java/org/openqa/selenium/ContextAware.java` is a package-shadowing shim
needed because Appium `java-client 9.4.0` still references `ContextAware` transitively,
while Selenium removed it in 4.19+.

**Proper fix:** add explicit `<dependencyManagement>` in `pom.xml` to pin
`selenium-java` to a compatible version (4.18.x), then delete the shim file.
Verify with `mvn clean test-compile`.

---

## Infrastructure & CI/CD

### INF-1: GitHub Actions — automated runs on PR / push
Add `.github/workflows/smoke.yml` that:
- Spins up a macOS runner with Xcode + Simulator
- Starts Appium server as a background step
- Runs `mvn test -Dgroups=smoke`
- Uploads Allure results as a build artifact
- Posts test summary as a PR comment

### INF-2: Multi-device matrix
Extend the CI matrix to run smoke tests on 2–3 iOS simulator versions (e.g. 17.5, 18.0, 18.2)
in parallel. Use `strategy.matrix` in GitHub Actions + `-Dplatform.version` / `-Ddevice.name`.

---

## Framework Improvements

### FW-1: Android support
Add `AndroidDriverFactory` + `AndroidOptions`, platform detection in `DriverManager`
(select factory based on `-Dplatform.name`). Locators: audit pages for iOS-only
`@iOSXCUITFindBy` and introduce `@AndroidFindBy` counterparts or switch to
`@FindBy` with platform-agnostic accessibility ids where possible.

### FW-2: Allure step annotations in page methods
Annotate page actions with `@Step` (e.g. `@Step("Tap 'Math Problems'")`).
This adds a step-by-step execution log in the Allure report and removes the need
for manual log statements in most pages.

### FW-3: Data-driven tests with `@DataProvider`
Paywall price assertions currently use hardcoded `TestData` constants.
Replace with a `@DataProvider` that reads price expectations from a JSON/CSV file
so QA can update expected values without touching Java source.

### FW-4: Environment profiles (dev / staging / prod)
Extend `AppiumConfig` `@Config.Sources` with a third tier:
`classpath:config-${env}.properties` inserted between `system:properties`
and `config.properties`. Owner's MERGE policy silently skips the file if
`-Denv` is not set, so there is no regression for local runs.
Create `config-dev.properties` and `config-staging.properties` with only
the keys that differ (e.g. `bundle.id`, `no.reset`, `wait.timeout`).
Usage: `mvn test -Denv=staging -Dgroups=regression`.

### FW-5: Programmatic Appium server start via `AppiumServiceBuilder`
Add `appium.autostart=false` key to `AppiumConfig`.
Create `AppiumServerManager` (uses `AppiumServiceBuilder`, `usingAnyFreePort()`)
and `AppiumServerListener implements ISuiteListener` that starts/stops the server
around the suite when `appium.autostart=true`.
`IOSDriverFactory` delegates URL resolution to `AppiumServerManager.resolveUrl()`
which returns the running service URL or falls back to `config.appiumServerUrl()`.
Register `AppiumServerListener` in `testng.xml`.
Benefit: CI runners need no pre-installed Appium server; `mvn test -Dappium.autostart=true` is self-contained.

### FW-6: Permission dialog handling in a Flow layer
`CameraPage.uploadFirstImageFromLibrary()` assumes Photos "All Photos" access is
already granted. Add a `PermissionFlow` utility that detects and dismisses iOS
system permission alerts (Photos, Camera, Notifications) using
`driver.switchTo().alert()` or XCUITest's native alert handling.

### FW-7: Allure `@Epic` / `@Feature` / `@Story` annotations on tests
Add Allure BDD-style annotations to test classes and methods. Example:
`@Epic("Monetization")`, `@Feature("Main Paywall")`, `@Story("Cold start paywall")`.
This groups tests logically in the Allure report and gives reviewers a clear picture
of functional coverage without reading the code.

### FW-8: `BaseComponent` base class for reusable UI components
`TabBar` currently extends `BasePage`, but semantically it is a page component,
not a standalone screen. Introduce `BaseComponent extends BasePage` (or rename
`BasePage` → `BaseScreenElement`) so that components have their own semantic layer.
This improves readability and signals intent when the team adds more shared components
(e.g. search bars, navigation headers).

### FW-9: Test image fixture management via `@photo` push
`CameraPage.uploadFirstImageFromLibrary()` currently selects the most recent photo
already present on the device/simulator. For deterministic test runs add a
`pushTestImageToDevice()` helper that uses `IOSDriver.pushFile("@photo", bytes)`
to inject a known fixture image before the picker opens. Store the image in
`src/main/resources/images/` so it ships with the page-object library.
