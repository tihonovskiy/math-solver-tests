# TODO — Planned Improvements

---

## Infrastructure & CI/CD

### INF-1: CI/CD pipeline (GitHub Actions + cloud device farm)

**Why CI is not configured yet:** the app under test is installed from the App Store
(production build). Simulator-based CI (GitHub Actions macOS runner) is not possible
because App Store apps cannot run on iOS simulators — only on real devices.

**What is needed for a full CI pipeline:**
1. **Access to a test build** (`.ipa` / `.app`) signed for development or ad-hoc distribution,
   **or** a cloud device farm account (BrowserStack, Sauce Labs, AWS Device Farm) that
   provides real iOS devices.
2. Once either is available, the pipeline would:
   - Spin up a macOS runner (or call the device farm API)
   - Start Appium server (or use `appium.autostart=true`)
   - Run `mvn test -Dgroups=smoke`
   - Upload Allure results as a build artifact
   - Post test summary as a PR comment
3. Extend with a multi-device matrix (`strategy.matrix`) to run smoke tests across
   2–3 iOS versions in parallel.

---

## Framework Improvements

### FW-1: Data-driven tests with `@DataProvider` + JSON
Currently paywall assertions use `TestData` constants — one fixed set of expected values.
`@DataProvider` makes sense **when there are multiple data sets to verify**, for example:

- App supports multiple regions/currencies (USD, EUR, GBP) — each with its own price tier
- A/B test is active and two price variants need to be checked simultaneously
- Multiple subscription plans are added (monthly, yearly, lifetime)

**Implementation sketch when the time comes:**
- Add `jackson-databind` to `pom.xml`
- Create `src/main/resources/data/paywall-prices.json` with an array of price configurations
- Create `core/data/JsonDataReader` that maps JSON rows to `Object[][]`
- Add `@DataProvider` to paywall test classes; each row becomes a separate test run in Allure

Until the app has multiple verifiable price configurations, `TestData` constants are simpler and equally correct.

### FW-2: Android support
Add `AndroidDriverFactory` + `AndroidOptions`, platform detection in `DriverManager`
(select factory based on `-Dplatform.name`). Locators: audit pages for iOS-only
`@iOSXCUITFindBy` and introduce `@AndroidFindBy` counterparts or switch to
`@FindBy` with platform-agnostic accessibility ids where possible.

### FW-3: Environment profiles (dev / staging / prod)
Extend `AppiumConfig` `@Config.Sources` with a third tier:
`classpath:config-${env}.properties` inserted between `system:properties`
and `config.properties`. Owner's MERGE policy silently skips the file if
`-Denv` is not set, so there is no regression for local runs.
Create `config-dev.properties` and `config-staging.properties` with only
the keys that differ (e.g. `bundle.id`, `no.reset`, `wait.timeout`).
Usage: `mvn test -Denv=staging -Dgroups=regression`.

### FW-4: Permission dialog handling in a Flow layer
`CameraPage.uploadFirstImageFromLibrary()` assumes Photos "All Photos" access is
already granted. Add a `PermissionFlow` utility that detects and dismisses iOS
system permission alerts (Photos, Camera, Notifications) using
`driver.switchTo().alert()` or XCUITest's native alert handling.

### FW-5: Test image fixture management via `@photo` push
`CameraPage.uploadFirstImageFromLibrary()` currently selects the most recent photo
already present on the device/simulator. For deterministic test runs add a
`pushTestImageToDevice()` helper that uses `IOSDriver.pushFile("@photo", bytes)`
to inject a known fixture image before the picker opens. Store the image in
`src/main/resources/images/` so it ships with the page-object library.

---

## Known Technical Debt

### TD-1: Resolve `ContextAware` compatibility shim
`src/main/java/org/openqa/selenium/ContextAware.java` is a package-shadowing shim
needed because Appium `java-client 9.4.0` still references `ContextAware` transitively,
while Selenium removed it in 4.19+.

**Proper fix:** add explicit `<dependencyManagement>` in `pom.xml` to pin
`selenium-java` to a compatible version (4.18.x), then delete the shim file.
Verify with `mvn clean test-compile`.
