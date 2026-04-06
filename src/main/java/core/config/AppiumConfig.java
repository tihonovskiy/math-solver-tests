package core.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config.properties"
})
public interface AppiumConfig extends Config {

    @Key("appium.server.url")
    String appiumServerUrl();

    @Key("platform.name")
    String platformName();

    @Key("platform.version")
    String platformVersion();

    @Key("device.name")
    String deviceName();

    @Key("automation.name")
    String automationName();

    @Key("bundle.id")
    String bundleId();

    @Key("app.path")
    String appPath();

    @Key("no.reset")
    @DefaultValue("false")
    boolean noReset();

    @Key("new.command.timeout")
    @DefaultValue("120")
    int newCommandTimeout();

    @Key("wait.timeout")
    @DefaultValue("15")
    int waitTimeoutSeconds();

    // Real device capabilities (leave empty for simulator)
    @Key("udid")
    String udid();

    @Key("xcode.org.id")
    String xcodeOrgId();

    @Key("xcode.signing.id")
    @DefaultValue("iPhone Developer")
    String xcodeSigningId();
}
