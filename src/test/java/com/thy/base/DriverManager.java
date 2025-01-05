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
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private final BrowserConfig.BrowserType browserType;
    private final String gridUrl;

    public DriverManager() {
        logger.info("Initializing DriverManager | DriverManager başlatılıyor");
        this.browserType = ConfigurationManager.getBrowser();
        this.gridUrl = ConfigurationManager.getGridUrl();
        logger.info("DriverManager initialized with browser: {} and grid URL: {} | Sürücü yöneticisi başlatıldı - Tarayıcı: {}, Grid URL: {}", 
            browserType, gridUrl != null ? gridUrl : "local", browserType, gridUrl != null ? gridUrl : "yerel");
    }

    private WebDriver createLocalDriver() {
        logger.info("Starting to create local {} driver | Yerel {} sürücüsü oluşturulmaya başlanıyor", browserType, browserType);
        WebDriver localDriver = switch (browserType) {
            case CHROME -> {
                logger.debug("Creating Chrome driver with options | Chrome sürücüsü opsiyonlarla oluşturuluyor");
                yield new ChromeDriver(BrowserConfig.getChromeOptions());
            }
            case FIREFOX -> {
                logger.debug("Creating Firefox driver with options | Firefox sürücüsü opsiyonlarla oluşturuluyor");
                yield new FirefoxDriver(BrowserConfig.getFirefoxOptions());
            }
            case EDGE -> {
                logger.debug("Creating Edge driver with options | Edge sürücüsü opsiyonlarla oluşturuluyor");
                yield new EdgeDriver(BrowserConfig.getEdgeOptions());
            }
            case SAFARI -> {
                logger.debug("Creating Safari driver with options | Safari sürücüsü opsiyonlarla oluşturuluyor");
                yield new SafariDriver(BrowserConfig.getSafariOptions());
            }
        };
        logger.info("Local {} driver created successfully | Yerel {} sürücüsü başarıyla oluşturuldu", browserType, browserType);
        return localDriver;
    }

    private WebDriver createRemoteDriver() {
        logger.info("Starting to create remote {} driver | Uzak {} sürücüsü oluşturulmaya başlanıyor", browserType, browserType);
        try {
            logger.debug("Creating grid URI from URL: {} | Grid URI oluşturuluyor, URL: {}", gridUrl, gridUrl);
            URI gridUri = URI.create(gridUrl);
            
            WebDriver remoteDriver = switch (browserType) {
                case CHROME -> {
                    logger.debug("Creating remote Chrome driver | Uzak Chrome sürücüsü oluşturuluyor");
                    yield new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getChromeOptions());
                }
                case FIREFOX -> {
                    logger.debug("Creating remote Firefox driver | Uzak Firefox sürücüsü oluşturuluyor");
                    yield new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getFirefoxOptions());
                }
                case EDGE -> {
                    logger.debug("Creating remote Edge driver | Uzak Edge sürücüsü oluşturuluyor");
                    yield new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getEdgeOptions());
                }
                case SAFARI -> {
                    logger.debug("Creating remote Safari driver | Uzak Safari sürücüsü oluşturuluyor");
                    yield new RemoteWebDriver(gridUri.toURL(), BrowserConfig.getSafariOptions());
                }
            };
            logger.info("Remote {} driver created successfully | Uzak {} sürücüsü başarıyla oluşturuldu", browserType, browserType);
            return remoteDriver;
        } catch (MalformedURLException e) {
            logger.error("Invalid grid URL: {} - Error: {} | Geçersiz grid URL'si: {} - Hata: {}", gridUrl, e.getMessage(), gridUrl, e.getMessage());
            throw new RuntimeException("Failed to create Remote WebDriver | Uzak WebDriver oluşturulamadı", e);
        }
    }

    public WebDriver getDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver == null) {
            logger.debug("No active WebDriver found, creating new instance | Aktif WebDriver bulunamadı, yeni örnek oluşturuluyor");
            WebDriver newDriver = gridUrl != null ? createRemoteDriver() : createLocalDriver();
            driver.set(newDriver);
            logger.info("New WebDriver instance created and set | Yeni WebDriver örneği oluşturuldu ve ayarlandı");
            return newDriver;
        }
        logger.debug("Returning existing WebDriver instance | Mevcut WebDriver örneği döndürülüyor");
        return currentDriver;
    }

    private void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            logger.debug("Attempting to quit WebDriver | WebDriver kapatılmaya çalışılıyor");
            try {
                currentDriver.quit();
                driver.remove();
                logger.info("WebDriver successfully closed and removed | WebDriver başarıyla kapatıldı ve kaldırıldı");
            } catch (Exception e) {
                logger.error("Error while closing WebDriver: {} | WebDriver kapatılırken hata oluştu: {}", 
                    e.getMessage(), e.getMessage());
                logger.debug("Stack trace: ", e);
            }
        } else {
            logger.debug("No active WebDriver instance to quit | Kapatılacak aktif WebDriver örneği yok");
        }
    }

    @Before
    public void setUp() {
        logger.info("Setting up test environment | Test ortamı hazırlanıyor");
        getDriver();
        logger.info("Test environment setup completed | Test ortamı kurulumu tamamlandı");
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("Starting test environment cleanup | Test ortamı temizlemeye başlanıyor");
        if (scenario != null) {
            logger.info("Scenario '{}' finished with status: {} | Senaryo '{}' durumu: {}", 
                scenario.getName(), scenario.getStatus(), scenario.getName(), scenario.getStatus());
        }
        quitDriver();
        logger.info("Test environment cleanup completed | Test ortamı temizleme tamamlandı");
    }
}
