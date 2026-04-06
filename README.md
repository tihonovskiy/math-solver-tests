# Math Solver — iOS UI Test Automation

End-to-end test suite for the **MathSolver** iOS application.
Built with Java 21 · Appium 9.4 · TestNG · AssertJ · Allure.

---

## Prerequisites

| Tool | Version | Notes |
|------|---------|-------|
| Java | 21+ | `java -version` |
| Maven | 3.9+ | `mvn -version` |
| Xcode | 15+ | For Simulator / XCUITest driver |
| Appium | 2.x | `npm i -g appium` |
| XCUITest driver | latest | `appium driver install xcuitest` |
| iOS Simulator | 18.0+ | iPhone 16 image (configurable) |

---

## Setup

```bash
git clone <repo-url>
cd math-solver-tests

# Start Appium server (leave running in a separate terminal)
appium

# Install dependencies
mvn clean install -DskipTests
```

---

## Running Tests

### All tests (default: simulator, iPhone 16, iOS 18.0)
```bash
mvn test
```

### Smoke suite only
```bash
mvn test -Dgroups=smoke
```

### Regression suite only
```bash
mvn test -Dgroups=regression
```

### Parallel execution (N classes at a time)
```bash
mvn test -Dthread.count=4
```

### Override device / OS version
```bash
mvn test -Dplatform.version=26.4 -Ddevice.name="iPhone 16 Pro"
```

---

## Real Device Setup

Pass the following `-D` flags (or add them to a gitignored `config.local.properties`):

```bash
mvn test \
  -Dudid=<device-udid> \
  -Dxcode.org.id=<your-apple-team-id> \
  -Dxcode.signing.id="Apple Development" \
  -Dbundle.id=com.worbert.mathapp
```

To find your device UDID: `xcrun xctrace list devices`

---

## Allure Report

```bash
# Generate and open report after a test run
mvn allure:serve

# Or generate static HTML
mvn allure:report
# → open target/site/allure-maven-plugin/index.html
```

---

## Project Structure

```
src/
├── main/java/
│   ├── core/
│   │   ├── BaseTest.java      # @BeforeMethod/@AfterMethod, launchToMainScreen()
│   │   ├── config/            # Owner-based type-safe config (AppiumConfig, ConfigProvider)
│   │   ├── data/              # TestData — single source of truth for expected UI strings
│   │   ├── driver/            # DriverManager (ThreadLocal), IOSDriverFactory
│   │   ├── listeners/         # ScreenshotListener, RetryAnalyzer(Listener)
│   │   └── utils/             # Waits — centralized explicit waits
│   └── pages/                 # Page Object Model
│       ├── components/        # Reusable UI components (TabBar)
│       └── popups/            # Modal screens (BasePopup, PaywallPage, ExpertsPaywallPage)
├── main/resources/
│   └── config.properties      # Default config (simulator)
└── test/
    ├── java/tests/            # Test classes (MainScreenTest, SettingsTest)
    │   └── paywalls/          # Paywall tests (MainPaywallTest, ExpertsPaywallTest)
    └── resources/
        ├── testng.xml         # Suite definition + listeners
        └── allure.properties
```

---

## Selenium Compatibility Shims

Appium `java-client 9.4.0` transitively pulls Selenium 4.41, which removed
`ContextAware`, `LocationContext`, and related classes. Appium still references
them internally, so compilation fails without stubs on the classpath.

The files under `src/main/java/org/openqa/selenium/` are **minimal compatibility
shims** that satisfy the linker. Each file has a Javadoc explaining why it exists.
See `TODO.md` (TD-1) for the long-term fix (pin Selenium via `<dependencyManagement>`).

---

## Configuration

Default config: `src/main/resources/config.properties` (simulator, no real-device creds).
Local overrides: create `config.local.properties` in project root (gitignored).
CLI overrides always win: `-Dbundle.id=...` `-Dplatform.version=...` etc.
