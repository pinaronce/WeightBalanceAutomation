package com.thy.steps;

import com.thoughtworks.gauge.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.thy.methods.BaseMethods;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class BaseSteps {
    private static final Logger logger = LogManager.getLogger(BaseSteps.class);
    private final BaseMethods baseMethods = new BaseMethods();

    @Step("Navigate to the <url>")
    public void navigateToURL(String url) {
        logger.info("Navigating to URL: " + url);
        baseMethods.navigateToURL(url);
    }

    @Step("Refresh the current page")
    public void refreshPage() {
        logger.info("Refreshing the current page.");
        baseMethods.refreshPage();
    }

    @Step("Switch to the new tab at index <tabIndex>")
    public void switchToTab(int tabIndex) {
        logger.info("Switching to tab at index: " + tabIndex);
        baseMethods.switchTab(tabIndex);
    }

    @Step("Click on <page> page's <elementName> element")
    public void clickElement(String page, String elementName) {
        baseMethods.click(page, elementName);
    }

    @Step("Click on the <page> page's <elementName> element if it exists")
    public void clickIfElementExists(String page, String elementName) {
        baseMethods.clickIfElementExists(page, elementName);
    }

    @Step("Wait <value> seconds")
    public void waitForSeconds(int value) {
        logger.info("Waiting for " + value + " seconds.");
        baseMethods.waitBySeconds(value);
    }

    @Step("Write <text> to the <page> page's <elementName> element")
    public void enterTextIntoPageElement(String text, String page, String elementName) {
        logger.info("Entering text '" + text + "' into element '" + elementName + "' on page '" + page + "'.");
        WebElement element = baseMethods.findElement(page, elementName, "visible");
        baseMethods.executeAndLog(() -> element.sendKeys(text), logger,
                "Successfully entered text into '" + elementName + "' on page '" + page + "'.",
                "Failed to enter text into '" + elementName + "' on page '" + page + "'.");
    }

    @Step("Scroll down the <page> page and find the <elementName> element")
    public void scrollToElement(String page, String elementName) {
        logger.info("Scrolling to element '" + elementName + "' on page '" + page + "'.");
        WebElement element = baseMethods.findElement(page, elementName, "visible");
        baseMethods.scrollToElement(element);
    }

    @Step("Press Enter key on the <page> page on the <elementName> element")
    public void pressEnterKey(String page, String elementName) {
        logger.info("Pressing Enter key on element '" + elementName + "' on page '" + page + "'.");
        WebElement element = baseMethods.findElement(page, elementName, "visible");
        baseMethods.executeAndLog(() -> element.sendKeys(Keys.ENTER), logger,
                "Successfully pressed Enter key on element: " + elementName + " on page: " + page,
                "Failed to press Enter key on element: " + elementName + " on page: " + page);
    }

    @Step("Verify <page> page's <elementName> element exists, fail with <errorMessage> if absent")
    public void checkElementExistenceOrFail(String page, String elementName, String errorMessage) {
        logger.info("Checking existence of element '" + elementName + "' on page '" + page + "'.");
        baseMethods.findElement(page, elementName, "visible");
        logger.info("Element '" + elementName + "' exists on page '" + page + "'.");
    }

    @Step("Verify that the text of <page> page's <elementName> element is <expectedText>")
    public void verifyElementTextIgnoreCase(String page, String elementName, String expectedText) {
        logger.info("Verifying text of element '" + elementName + "' on page '" + page + "' against expected text '" + expectedText + "'.");
        String actualText = baseMethods.getElementText(page, elementName);
        baseMethods.assertTextIgnoreCase(actualText, expectedText);
    }

    @Step("On the <pageName> page, save the text value of the <elementName> element as <saveKey>")
    public void saveTextValueOfElement(String pageName, String elementName, String saveKey) {
        logger.info("Saving text value of element '" + elementName + "' on page '" + pageName + "' as '" + saveKey + "'.");
        String text = baseMethods.getElementText(pageName, elementName);
        baseMethods.saveValue(saveKey, text);
    }

    @Step("On the <pageName> page, compare the text of the <elementName> element to the saved value with key <expectedValueKey>")
    public void compareElementTextToSavedValue(String pageName, String elementName, String expectedValueKey) {
        logger.info("Comparing text of element '" + elementName + "' on page '" + pageName + "' to saved value with key '" + expectedValueKey + "'.");
        String actualText = baseMethods.getElementText(pageName, elementName);
        String expectedValue = baseMethods.getStoredValue(expectedValueKey);
        baseMethods.assertTextContains(actualText, expectedValue, expectedValueKey);
    }
}