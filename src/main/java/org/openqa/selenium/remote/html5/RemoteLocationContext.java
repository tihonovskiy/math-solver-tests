package org.openqa.selenium.remote.html5;

import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.remote.ExecuteMethod;

/**
 * Compatibility shim: removed from Selenium 4.26+ but still referenced transitively
 * by io.appium.java_client via SupportsLocation default method implementations.
 */
public class RemoteLocationContext implements LocationContext {

    @SuppressWarnings("unused")
    public RemoteLocationContext(ExecuteMethod executeMethod) {}

    @Override
    public Location location() {
        throw new UnsupportedOperationException("Compatibility shim only");
    }

    @Override
    public void setLocation(Location location) {
        throw new UnsupportedOperationException("Compatibility shim only");
    }
}
