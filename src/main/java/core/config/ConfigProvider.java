package core.config;

import org.aeonbits.owner.ConfigFactory;

public final class ConfigProvider {

    private static final AppiumConfig CONFIG = ConfigFactory.create(AppiumConfig.class);

    private ConfigProvider() {
    }

    public static AppiumConfig get() {
        return CONFIG;
    }
}
