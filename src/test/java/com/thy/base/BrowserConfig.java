package com.thy.base;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserConfig {

    public enum BrowserType {
        CHROME, FIREFOX, EDGE, SAFARI
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu", "--incognito", "--no-sandbox", "--disable-dev-shm-usage",
                "--disable-extensions", "--disable-popup-blocking", "--start-maximized",
                "disable-infobars");
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--private", "--start-maximized");
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("privacy.trackingprotection.enabled", true);
        options.addPreference("network.cookie.cookieBehavior", 2);
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate", "--disable-gpu", "--start-maximized", "disable-infobars");
        return options;
    }

    public static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setUseTechnologyPreview(false);
        options.setAutomaticInspection(false);
        options.setAutomaticProfiling(false);
        return options;
    }
}
