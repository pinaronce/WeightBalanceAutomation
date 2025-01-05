package com.thy.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationManager {
    private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);

    private static final BrowserConfig.BrowserType DEFAULT_BROWSER = BrowserConfig.BrowserType.CHROME;
    private static final String GRID_URL = "http://localhost:4444/wd/hub";
    private static final boolean USE_GRID = false;

    public static BrowserConfig.BrowserType getBrowser() {
        try {
            String browserProperty = System.getProperty("browser");
            BrowserConfig.BrowserType selectedBrowser = browserProperty != null ? 
                BrowserConfig.BrowserType.valueOf(browserProperty.toUpperCase()) : 
                DEFAULT_BROWSER;
            
            logger.info("Selected browser: {} (Default: {}) | Seçilen tarayıcı: {} (Varsayılan: {})", 
                selectedBrowser, DEFAULT_BROWSER, selectedBrowser, DEFAULT_BROWSER);
            return selectedBrowser;
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid browser type. Using default: {} | Geçersiz tarayıcı tipi. Varsayılan kullanılıyor: {}", 
                DEFAULT_BROWSER, DEFAULT_BROWSER);
            return DEFAULT_BROWSER;
        }
    }

    public static String getGridUrl() {
        if (!USE_GRID) {
            logger.info("Grid is disabled. Running tests locally | Grid kapalı. Testler lokalde çalışacak");
            return null;
        }

        String configuredUrl = System.getProperty("gridUrl", GRID_URL);
        logger.info("Grid URL: {}, Status: {} | Grid URL: {}, Durum: {}", 
            configuredUrl, USE_GRID ? "enabled" : "disabled", configuredUrl, USE_GRID ? "açık" : "kapalı");
        return configuredUrl;
    }
}
