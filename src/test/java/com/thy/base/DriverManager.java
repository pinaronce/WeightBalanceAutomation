package com.thy.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class DriverManager {
    protected static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final String DEFAULT_BROWSER = "CHROME";
    private static final String USE_GRID_PROPERTY = "useGrid";
    private static final String GRID_URL_PROPERTY = "gridUrl";
    private static final String CHROME_DRIVER_PATH = "drivers/chromedriver.exe";

    private final BrowserConfig.BrowserType selectedBrowser;
    private final boolean useGrid;
    private final String gridUrl;

    static {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
    }

    public DriverManager() {
        this.selectedBrowser = BrowserConfig.BrowserType.valueOf(System.getProperty("browser", DEFAULT_BROWSER).toUpperCase());
        this.useGrid = Boolean.parseBoolean(System.getProperty(USE_GRID_PROPERTY, "false"));
        this.gridUrl = System.getProperty(GRID_URL_PROPERTY, "http://localhost:4444/wd/hub");
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = useGrid ? setupRemoteWebDriver() : setupLocalWebDriver();
            configureDriver();
            logger.info("Test is running " + (useGrid ? "on Grid at URL: " + gridUrl : "locally"));
        }
        return driver;
    }

    private WebDriver setupLocalWebDriver() {
        return switch (selectedBrowser) {
            case CHROME -> new ChromeDriver(BrowserConfig.getChromeOptions());
            case FIREFOX -> new FirefoxDriver(BrowserConfig.getFirefoxOptions());
            case EDGE -> new EdgeDriver(BrowserConfig.getEdgeOptions());
            case SAFARI -> new SafariDriver(BrowserConfig.getSafariOptions());
        };
    }

    private WebDriver setupRemoteWebDriver() {
        try {
            URI gridUri = URI.create(this.gridUrl);
            return switch (selectedBrowser) {
                case CHROME -> new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getChromeOptions());
                case FIREFOX -> new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getFirefoxOptions());
                case EDGE -> new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getEdgeOptions());
                case SAFARI -> new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getSafariOptions());
            };
        } catch (MalformedURLException e) {
            logger.error("Invalid grid URL: " + gridUrl, e);
            throw new RuntimeException("Invalid grid URL: " + gridUrl, e);
        }
    }

    private void configureDriver() {
        if (driver != null) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        }
    }

    public void cleanUpTestEnvironment() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver closed successfully");
        }
    }
}