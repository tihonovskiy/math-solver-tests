package org.openqa.selenium;

import java.util.Set;

/**
 * Compatibility shim: removed from Selenium 4.19+ but still referenced transitively
 * by io.appium.java_client via SupportsContextSwitching. Without this file on the
 * classpath, javac fails with "class file for org.openqa.selenium.ContextAware not found"
 * when compiling any class that uses IOSDriver (e.g. CameraPage.pushFile).
 */
public interface ContextAware {
    WebDriver context(String name);
    Set<String> getContextHandles();
    String getContext();
}
