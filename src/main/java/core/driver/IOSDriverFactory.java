package core.driver;

import core.config.AppiumConfig;
import core.config.ConfigProvider;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class IOSDriverFactory implements PlatformDriverFactory {

    private static final Logger log = LoggerFactory.getLogger(IOSDriverFactory.class);
    private final AppiumConfig config = ConfigProvider.get();

    @Override
    public AppiumDriver create() {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName(config.platformName())
                .setPlatformVersion(config.platformVersion())
                .setDeviceName(config.deviceName())
                .setAutomationName(config.automationName())
                .setNoReset(config.noReset())
                .setNewCommandTimeout(Duration.ofSeconds(config.newCommandTimeout()));

        // Prefer app path if provided (installs fresh build); otherwise launch installed bundle.
        if (config.appPath() != null && !config.appPath().isBlank()) {
            options.setApp(config.appPath());
        } else if (config.bundleId() != null && !config.bundleId().isBlank()) {
            options.setBundleId(config.bundleId());
        }

        // Real device — only apply when udid is present (simulator works without these)
        if (config.udid() != null && !config.udid().isBlank()) {
            options.setUdid(config.udid());
            options.setCapability("appium:xcodeOrgId", config.xcodeOrgId());
            options.setCapability("appium:xcodeSigningId", config.xcodeSigningId());
            log.info("Real device mode: udid={}", config.udid());
        }

        log.info("Creating IOSDriver: device='{}' iOS={} bundleId={}",
                config.deviceName(), config.platformVersion(), config.bundleId());

        return new IOSDriver(AppiumServerManager.resolveUrl(), options);
    }
}
