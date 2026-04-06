package core.listeners;

import core.config.ConfigProvider;
import core.driver.AppiumServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class AppiumServerListener implements ISuiteListener {

    private static final Logger log = LoggerFactory.getLogger(AppiumServerListener.class);

    @Override
    public void onStart(ISuite suite) {
        if (ConfigProvider.get().appiumAutostart()) {
            log.info("appium.autostart=true — starting local Appium server...");
            AppiumServerManager.start();
        }
    }

    @Override
    public void onFinish(ISuite suite) {
        AppiumServerManager.stop();
    }
}
