package com.thy.methods;

import com.thy.base.DriverManager;
import com.thy.elements.LocatorRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseMethods extends DriverManager {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private static final Map<String, String> savedValues = new HashMap<>();
    private static final Duration DEFAULT_WAIT = Duration.ofSeconds(10);

    public BaseMethods() {
        this.driver = new DriverManager().getDriver();
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT);
    }

    public void navigateToURL(String url) {
        driver.get(url);
    }

    public void click(String page, String elementName) {
        WebElement element = findElement(page, elementName);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void verifyURL(String expectedURL) {
        wait.until(ExpectedConditions.urlToBe(expectedURL));
        Assert.assertEquals("URL does not match!", expectedURL, driver.getCurrentUrl());
    }

    public void clickIfElementExists(String page, String elementName) {
        try {
            WebElement element = findElement(page, elementName);
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
            }
        } catch (Exception ignored) {
            // Element not found or not clickable, continue
        }
    }

    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void enterTextIntoElement(String text, String page, String elementName) {
        WebElement element = findElement(page, elementName);
        element.clear();
        element.sendKeys(text);
    }

    public void pressEnterOnElement(String page, String elementName) {
        WebElement element = findElement(page, elementName);
        element.sendKeys(Keys.ENTER);
    }

    public void checkElementExistenceOrFail(String page, String elementName, String errorMessage) {
        try {
            WebElement element = findElement(page, elementName);
            Assert.assertTrue(errorMessage, element.isDisplayed());
        } catch (Exception e) {
            Assert.fail(errorMessage);
        }
    }

    public void verifyElementTextIgnoreCase(String page, String elementName, String expectedText) {
        WebElement element = findElement(page, elementName);
        String actualText = element.getText().trim();
        Assert.assertTrue(
            String.format("Expected text: '%s' but found: '%s'", expectedText, actualText),
            actualText.equalsIgnoreCase(expectedText)
        );
    }

    public void saveTextValueOfElement(String page, String elementName, String key) {
        WebElement element = findElement(page, elementName);
        savedValues.put(key, element.getText().trim());
    }

    public void compareElementTextToSavedValue(String page, String elementName, String key) {
        String savedValue = savedValues.get(key);
        Assert.assertNotNull("No saved value found for key: " + key, savedValue);
        verifyElementTextIgnoreCase(page, elementName, savedValue);
    }

    public void verifyPageTitle(String page, String expectedTitle) {
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        Assert.assertEquals("Page title does not match!", expectedTitle, driver.getTitle());
    }

    public void scrollToElement(String page, String elementName) {
        WebElement element = findElement(page, elementName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        waitBySeconds(1); // Wait for scroll to complete
    }

    public void switchToIframe(String frameName) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    }

    public void switchOutOfIframe() {
        driver.switchTo().defaultContent();
    }

    public void verifyElementAttribute(String page, String elementName, String attributeName, String expectedValue) {
        WebElement element = findElement(page, elementName);
        String actualValue = element.getAttribute(attributeName);
        Assert.assertEquals(
            String.format("Attribute '%s' value mismatch", attributeName),
            expectedValue,
            actualValue
        );
    }

    public void selectDropdownOption(String page, String dropdown, String option) {
        WebElement element = findElement(page, dropdown);
        new Select(element).selectByVisibleText(option);
    }

    public void uploadFile(String page, String elementName, String filePath) {
        WebElement element = findElement(page, elementName);
        element.sendKeys(filePath);
    }

    public void verifyImageVisibility(String page, String elementName) {
        WebElement img = findElement(page, elementName);
        Assert.assertTrue(
            "Image is not displayed properly",
            (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                img
            )
        );
    }

    public void verifyElementIsEnabled(String page, String elementName) {
        WebElement element = findElement(page, elementName);
        Assert.assertTrue("Element is not enabled!", element.isEnabled());
    }

    public void verifyElementIsDisabled(String page, String elementName) {
        WebElement element = findElement(page, elementName);
        Assert.assertFalse("Element is enabled!", element.isEnabled());
    }

    public void switchTab(int tabIndex) {
        wait.until(driver -> driver.getWindowHandles().size() > tabIndex);
        String[] handles = driver.getWindowHandles().toArray(new String[0]);
        driver.switchTo().window(handles[tabIndex]);
    }

    public void refreshPage() {
        driver.navigate().refresh();
        waitBySeconds(1);
    }

    private WebElement findElement(String page, String elementName) {
        By locator = LocatorRepository.getLocator(page, elementName);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}