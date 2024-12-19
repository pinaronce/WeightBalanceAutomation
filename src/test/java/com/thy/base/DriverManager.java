package com.thy.base;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    public static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final String DEFAULT_BROWSER = "CHROME";
    private static final String USE_GRID_PROPERTY = "useGrid";
    private static final String GRID_URL_PROPERTY = "gridUrl";

    protected static WebDriver driver;
    private final BrowserConfig.BrowserType selectedBrowser;
    private final boolean useGrid;
    private final String gridUrl;

    public DriverManager() {
        this.selectedBrowser = BrowserConfig.BrowserType.valueOf(System.getProperty("browser", DEFAULT_BROWSER).toUpperCase());
        this.useGrid = Boolean.parseBoolean(System.getProperty(USE_GRID_PROPERTY, "false"));
        this.gridUrl = System.getProperty(GRID_URL_PROPERTY, "http://localhost:4444/wd/hub");
    }

    @BeforeScenario
    public void initializeTestEnvironment() {

        driver = useGrid ? setupRemoteWebDriver() : setupLocalWebDriver();
        maximizeBrowserWindow();

        logger.info("Test is running {}.", useGrid ? "on Grid at URL: " + gridUrl : "locally");
    }


    @AfterScenario
    public void cleanUpTestEnvironment() {
        if (driver != null) {
            driver.quit();
        }
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
            URL gridUrl = new URL(this.gridUrl);
            return switch (selectedBrowser) {
                case CHROME -> new RemoteWebDriver(gridUrl, BrowserConfig.getChromeOptions());
                case FIREFOX -> new RemoteWebDriver(gridUrl, BrowserConfig.getFirefoxOptions());
                case EDGE -> new RemoteWebDriver(gridUrl, BrowserConfig.getEdgeOptions());
                case SAFARI -> new RemoteWebDriver(gridUrl, BrowserConfig.getSafariOptions());
            };
        } catch (MalformedURLException e) {
            logger.error("Invalid grid URL: {}", gridUrl, e);
            throw new RuntimeException("Invalid grid URL: " + gridUrl, e);
        }
    }

    private void maximizeBrowserWindow() {
        if (driver != null) {
            driver.manage().window().maximize();
        }
    }
}
