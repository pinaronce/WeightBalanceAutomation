package com.thy.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserConfig {
    private static final Logger logger = LogManager.getLogger(BrowserConfig.class);

    public enum BrowserType {
        CHROME, FIREFOX, EDGE, SAFARI
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setAcceptInsecureCerts(true);
        
        logger.info("Chrome options configured");
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("-private");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setAcceptInsecureCerts(true);
        
        logger.info("Firefox options configured");
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setAcceptInsecureCerts(true);
        
        logger.info("Edge options configured");
        return options;
    }

    public static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(false);
        options.setAutomaticProfiling(false);
        options.setUseTechnologyPreview(false);
        options.setCapability("safari.cleanSession", true);
        options.setCapability("safari.options.dataDir", "/tmp/safari_data");
        options.setCapability("safari.options.port", 0);
        
        logger.info("Safari options configured");
        return options;
    }
}
