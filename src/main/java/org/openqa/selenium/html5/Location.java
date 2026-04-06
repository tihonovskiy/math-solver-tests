package org.openqa.selenium.html5;

/**
 * Compatibility shim: removed from Selenium 4.26+ but still referenced
 * transitively by Appium java-client 9.4.0 via RemoteLocationContext.
 */
public class Location {
    public Location(double latitude, double longitude, double altitude) {}
}
