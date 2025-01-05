package com.thy.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    private final BrowserConfig.BrowserType browserType;
    private final String gridUrl;
    private final boolean useGrid;

    public DriverManager() {
        this.browserType = ConfigurationManager.getBrowserType();
        this.gridUrl = ConfigurationManager.getGridConfig();
        this.useGrid = ConfigurationManager.useGrid();
    }

    private WebDriver setupLocalWebDriver() {
        return switch (browserType) {
            case CHROME -> new ChromeDriver(BrowserConfig.getChromeOptions());
            case FIREFOX -> new FirefoxDriver(BrowserConfig.getFirefoxOptions());
            case EDGE -> new EdgeDriver(BrowserConfig.getEdgeOptions());
            case SAFARI -> new SafariDriver(BrowserConfig.getSafariOptions());
        };
    }

    private WebDriver setupRemoteWebDriver() {
        try {
            URI gridUri = URI.create(gridUrl);
            return switch (browserType) {
                case CHROME -> new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getChromeOptions());
                case FIREFOX -> new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getFirefoxOptions());
                case EDGE -> new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getEdgeOptions());
                case SAFARI -> new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getSafariOptions());
            };
        } catch (MalformedURLException e) {
            logger.error("Invalid grid URL: {}", gridUrl);
            throw new RuntimeException("Failed to create Remote WebDriver", e);
        }
    }

    public WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(useGrid ? setupRemoteWebDriver() : setupLocalWebDriver());
            logger.info("Test is running {} with {} browser",
                    useGrid ? "on Grid at " + gridUrl : "locally", browserType);
        }
        return driver.get();
    }

    private void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
            logger.info("WebDriver closed and removed successfully");
        }
    }

    @Before
    public void setUp() {
        getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        quitDriver();
    }
}
