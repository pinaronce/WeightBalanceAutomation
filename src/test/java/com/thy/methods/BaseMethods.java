package com.thy.methods;

import com.thy.base.DriverManager;
import com.thy.elements.LocatorRepository;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class BaseMethods extends DriverManager {
    private final Map<String, String> storedValues = new HashMap<>();
    public static final int DEFAULT_TIMEOUT_SECONDS = 20;

    private static final Logger logger = LogManager.getLogger(BaseMethods.class);


    public WebElement waitForElement(String page, String elementName, String condition, int timeoutInSeconds) {
        By locator = getLocatorFromPage(page, elementName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        logger.info("Waiting for element '" + elementName + "' on page '" + page + "' to be " + condition + " within " + timeoutInSeconds + " seconds.");

        try {
            WebElement element = switch (condition.toLowerCase()) {
                case "visible" -> wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                case "clickable" -> wait.until(ExpectedConditions.elementToBeClickable(locator));
                case "present" -> wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                default -> throw new IllegalArgumentException("Unsupported condition: " + condition);
            };
            logger.info("Element '" + elementName + "' on page '" + page + "' is now " + condition + ".");
            return element;
        } catch (Exception e) {
            logger.error("Failed to meet condition: " + condition + " for element: " + elementName + " on page: " + page, e);
            throw e;
        }
    }

    public WebElement findElement(String page, String elementName, String condition) {
        return waitForElement(page, elementName, condition, DEFAULT_TIMEOUT_SECONDS);
    }

    public String getElementText(String page, String elementName) {
        WebElement element = findElement(page, elementName, "visible");
        return element.getText().trim();
    }

    public List<WebElement> getElements(String page, String elementName) {
        return waitForElement(page, elementName, "visible", DEFAULT_TIMEOUT_SECONDS).findElements(By.xpath(".//*"));
    }

    public void navigateToURL(String url) {
        logger.info("Navigating to URL: " + url);
        executeAndLog(() -> driver.get(url), logger, "Navigated to URL: " + url, "Navigation to URL failed: " + url);
    }

    public void refreshPage() {
        logger.info("Refreshing the current page.");
        executeAndLog(() -> driver.navigate().refresh(), logger, "Page refreshed successfully.", "Page refresh failed.");
    }

    public void saveValue(String saveKey, String value) {
        storedValues.put(saveKey, value);
        logger.info("Saved value for key '" + saveKey + "': " + value);
    }

    public String getStoredValue(String key) {
        return storedValues.get(key);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center', inline: 'nearest' });", element);
    }

    public void assertTextIgnoreCase(String actual, String expected) {
        Assertions.assertTrue(actual.equalsIgnoreCase(expected), "Expected text: '" + expected + "' does not match actual text: '" + actual + "'");
    }

    public void assertTextContains(String actual, String expected, String key) {
        Assertions.assertTrue(actual.contains(expected), "Expected text: '" + expected + "' associated with '" + key + "' does not match the actual text: '" + actual + "'.");
    }

    public void executeAndLog(Runnable action, Logger logger, String successMessage, String errorMessage) {
        try {
            action.run();
            logger.info(successMessage);
        } catch (Exception e) {
            logger.error(errorMessage + ": " + e.getMessage(), e);
            throw e;
        }
    }

    public void click(String page, String elementName) {
        WebElement element = findElement(page, elementName, "clickable");
        element.click();
    }

    public void clickIfElementExists(String page, String elementName) {
        List<WebElement> elements = driver.findElements(getLocatorFromPage(page, elementName));
        if (!elements.isEmpty()) {
            click(page, elementName);
        } else {
            logger.warn("Element '" + elementName + "' not found on page '" + page + "'. No action taken.");
        }
    }

    public By getLocatorFromPage(String page, String elementName) {
        Map<String, By> pageLocators = LocatorRepository.getPageElementLocators().get(page.toLowerCase());

        if (pageLocators == null) {
            throw new IllegalArgumentException("Page '" + page + "' not found in locator definitions.");
        }

        By locator = pageLocators.get(elementName.toLowerCase());

        if (locator == null) {
            throw new IllegalArgumentException("Element '" + elementName + "' not found on page '" + page + "'.");
        }

        return locator;
    }

    public void waitBySeconds(int seconds) {
        if (seconds > 0) {
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void switchTab(int tabIndex) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabIndex < 0 || tabIndex >= tabs.size()) {
            throw new IndexOutOfBoundsException("Tab index " + tabIndex + " is out of range. Total tabs: " + tabs.size());
        }
        driver.switchTo().window(tabs.get(tabIndex));
    }
}
