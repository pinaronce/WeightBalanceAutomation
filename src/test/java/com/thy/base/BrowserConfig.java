package com.thy.base;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

    public class BrowserConfig {

        public enum BrowserType {
            CHROME,
            FIREFOX,
            EDGE,
            SAFARI
        }

        public static ChromeOptions getChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-gpu");
            options.addArguments("--incognito");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--start-maximized");
            options.addArguments("disable-infobars");
            return options;
        }

        public static FirefoxOptions getFirefoxOptions() {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--private");
            options.addPreference("dom.webnotifications.enabled", false);
            options.addPreference("privacy.trackingprotection.enabled", true);
            options.addPreference("network.cookie.cookieBehavior", 2);
            options.addArguments("--start-maximized");
            return options;
        }

        public static EdgeOptions getEdgeOptions() {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--inprivate");
            options.addArguments("--disable-gpu");
            options.addArguments("--start-maximized");
            options.addArguments("disable-infobars");
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

