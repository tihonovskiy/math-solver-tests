package core.driver;

import core.config.ConfigProvider;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public final class AppiumServerManager {

    private static final Logger log = LoggerFactory.getLogger(AppiumServerManager.class);
    private static AppiumDriverLocalService service;

    private AppiumServerManager() {}

    public static void start() {
        if (service != null && service.isRunning()) {
            log.warn("Appium server is already running at {}", service.getUrl());
            return;
        }
        service = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .build();
        service.start();
        log.info("Appium server started at {}", service.getUrl());
    }

    public static void stop() {
        if (service == null) return;
        try {
            if (service.isRunning()) {
                service.stop();
                log.info("Appium server stopped");
            }
        } finally {
            service = null;
        }
    }

    public static URL resolveUrl() {
        if (service != null && service.isRunning()) {
            return service.getUrl();
        }
        try {
            return new URL(ConfigProvider.get().appiumServerUrl());
        } catch (MalformedURLException e) {
            throw new IllegalStateException(
                    "Invalid Appium server URL: " + ConfigProvider.get().appiumServerUrl(), e);
        }
    }
}
