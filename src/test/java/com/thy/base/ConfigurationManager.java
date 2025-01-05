package com.thy.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationManager {
        private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);

        private static final BrowserConfig.BrowserType DEFAULT_BROWSER = BrowserConfig.BrowserType.CHROME;
        private static final String DEFAULT_GRID_URL = "http://localhost:4444/wd/hub";
        private static final boolean USE_GRID = false;

        private static final String BROWSER_PROPERTY = "browser";
        private static final String GRID_URL_PROPERTY = "gridUrl";

        public static BrowserConfig.BrowserType getBrowserType() {
            String browser = System.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER.name());
            BrowserConfig.BrowserType browserType = BrowserConfig.BrowserType.valueOf(browser.toUpperCase());
            logger.info("Selected browser: {}", browserType);
            return browserType;
        }

        public static boolean useGrid() {
            return USE_GRID;
        }

        public static String getGridConfig() {
            String gridUrl = System.getProperty(GRID_URL_PROPERTY, DEFAULT_GRID_URL);
            logger.info("Grid status: {} with URL: {}", USE_GRID ? "enabled" : "disabled", gridUrl);
            return USE_GRID ? gridUrl : null;
        }
    }
