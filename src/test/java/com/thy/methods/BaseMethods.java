package com.thy.methods;

import com.thy.base.DriverManager;
import com.thy.elements.LocatorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;

public class BaseMethods extends DriverManager {
    private static final Map<String, String> savedValues = new HashMap<>();
    private static final int DEFAULT_TIMEOUT_SECONDS = 20;
    private static final Logger logger = LogManager.getLogger(BaseMethods.class);

    public void navigateToURL(String url) {
        try {
            driver.get(url);
            logger.info("Navigated to URL: {}", url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: {}", url, e);
            throw e;
        }
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            logger.info("Page refreshed successfully.");
        } catch (Exception e) {
            logger.error("Failed to refresh the page.", e);
            throw e;
        }
    }

    public void switchTab(int tabIndex) {
        try {
            var allWindowHandles = driver.getWindowHandles();
            driver.switchTo().window((String) allWindowHandles.toArray()[tabIndex]);
            logger.info("Switched to tab at index {}", tabIndex);
        } catch (Exception e) {
            logger.error("Failed to switch to tab at index {}", tabIndex, e);
            throw e;
        }
    }

    public void click(String page, String elementName) {
        WebElement element = findElement(page, elementName, "clickable");
        element.click();
        logger.info("Performed 'click' action on element '{}' located on page '{}'.", elementName, page);
    }

    public void clickIfElementExists(String page, String elementName) {
        try {
            WebElement element = findElement(page, elementName, "clickable");
            element.click();
            logger.info("Clicked on element '{}' on page '{}'", elementName, page);
        } catch (NoSuchElementException e) {
            logger.warn("Element '{}' not found on page '{}', skipping click", elementName, page);
        }
    }

    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.info("Waited for {} seconds", seconds);
        } catch (InterruptedException e) {
            logger.error("Error while waiting for {} seconds", seconds, e);
        }
    }

    public void enterTextIntoElement(String text, String page, String elementName) {
        WebElement element = findElement(page, elementName, "visible");
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' into element '{}' on page '{}'", text, elementName, page);
    }

    public void pressEnterOnElement(String page, String elementName) {
        WebElement element = findElement(page, elementName, "visible");
        element.sendKeys(Keys.RETURN);
        logger.info("Pressed Enter on element '{}' on page '{}'", elementName, page);
    }

    public void checkElementExistenceOrFail(String page, String elementName, String errorMessage) {
        logger.info("Checking existence of element '{}' on page '{}'", elementName, page);
        WebElement element = findElement(page, elementName, "visible");
        if (element == null) {
            logger.error(errorMessage);
            throw new NoSuchElementException(errorMessage);
        }
        logger.info("Element '{}' exists on page '{}'", elementName, page);
    }

    public void verifyElementTextIgnoreCase(String page, String elementName, String expectedText) {
        logger.info("Verifying text of element '{}' on page '{}' against expected text '{}'", elementName, page, expectedText);
        String actualText = getElementText(page, elementName);
        assertTextIgnoreCase(actualText, expectedText);
    }

    public void compareElementTextToSavedValue(String page, String elementName, String expectedValueKey) {
        logger.info("Comparing text of element '{}' on page '{}' to saved value with key '{}'", elementName, page, expectedValueKey);
        String actualText = getElementText(page, elementName);
        String expectedValue = getStoredValue(expectedValueKey);
        assertTextContains(actualText, expectedValue, expectedValueKey);
    }

    public String getElementText(String page, String elementName) {
        WebElement element = findElement(page, elementName, "visible");
        return element.getText().trim();
    }

    public String getStoredValue(String key) {
        return savedValues.get(key);
    }

    public void assertTextIgnoreCase(String actual, String expected) {
        Assertions.assertTrue(actual.equalsIgnoreCase(expected),
                "Expected text: '" + expected + "' does not match actual text: '" + actual + "'.");
    }

    public void assertTextContains(String actual, String expected, String key) {
        Assertions.assertTrue(actual.contains(expected),
                "Expected text: '" + expected + "' associated with '" + key + "' does not match the actual text: '" + actual + "'.");
    }

    public void checkElementExistence(String page, String elementName) {
        By locator = LocatorRepository.getLocator(page, elementName);
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element not found: " + page + " -> " + elementName);
        }
    }

    public void saveTextValueOfElement(String page, String elementName, String saveKey) {
        By locator = LocatorRepository.getLocator(page, elementName);
        WebElement element = driver.findElement(locator);
        savedValues.put(saveKey, element.getText());
    }

    private WebElement waitForElement(String page, String elementName, String condition, int timeoutInSeconds) {
        By locator = getLocatorFromPage(page, elementName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        try {
            WebElement element = switch (condition.toLowerCase()) {
                case "visible" -> wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                case "clickable" -> wait.until(ExpectedConditions.elementToBeClickable(locator));
                case "present" -> wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                default -> throw new IllegalArgumentException("Unsupported condition: " + condition);
            };
            return element;
        } catch (Exception e) {
            logger.error("Failed to find element '{}' with condition '{}' on page '{}'", elementName, condition, page, e);
            throw e;
        }
    }

    private WebElement findElement(String page, String elementName, String condition) {
        return waitForElement(page, elementName, condition, DEFAULT_TIMEOUT_SECONDS);
    }

    private By getLocatorFromPage(String page, String elementName) {
        return LocatorRepository.getLocator(page, elementName);
    }
}
