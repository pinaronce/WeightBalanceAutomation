package com.thy.steps;

import com.thoughtworks.gauge.Step;
import com.thy.methods.BaseMethods;

public class BaseSteps {
    private final BaseMethods baseMethods = new BaseMethods();

    @Step("Navigate to the <url>")
    public void navigateToURL(String url) {
        baseMethods.navigateToURL(url);
    }

    @Step("Refresh the current page")
    public void refreshPage() {
        baseMethods.refreshPage();  // This method is used
    }

    @Step("Switch to the new tab at index <tabIndex>")
    public void switchToTab(int tabIndex) {
        baseMethods.switchTab(tabIndex);  // This method is used
    }

    @Step("Click on <page> page's <elementName> element")
    public void clickElement(String page, String elementName) {
        baseMethods.click(page, elementName);  // This method is used
    }

    @Step("Click on the <page> page's <elementName> element if it exists")
    public void clickIfElementExists(String page, String elementName) {
        baseMethods.clickIfElementExists(page, elementName);  // This method is used
    }

    @Step("Wait <value> seconds")
    public void waitForSeconds(int value) {
        baseMethods.waitBySeconds(value);
    }

    @Step("Write <text> to the <page> page's <elementName> element")
    public void enterTextIntoPageElement(String text, String page, String elementName) {
        baseMethods.enterTextIntoElement(text, page, elementName);
    }

    @Step("Press enter on <page> page's <elementName> element")
    public void pressEnter(String page, String elementName) {
        baseMethods.pressEnterOnElement(page, elementName);
    }

    @Step("Check if <page> page's <elementName> element exists")
    public void checkElementExistence(String page, String elementName) {
        baseMethods.checkElementExistence(page, elementName);
    }

    @Step("Verify <page> page's <elementName> element exists, fail with <errorMessage> if absent")
    public void checkElementExistenceOrFail(String page, String elementName, String errorMessage) {
        baseMethods.checkElementExistenceOrFail(page, elementName, errorMessage);
    }

    @Step("Verify that the text of <page> page's <elementName> element is <expectedText>")
    public void verifyElementTextIgnoreCase(String page, String elementName, String expectedText) {
        baseMethods.verifyElementTextIgnoreCase(page, elementName, expectedText);
    }

    @Step("On the <pageName> page, save the text value of the <elementName> element as <saveKey>")
    public void saveTextValueOfElement(String pageName, String elementName, String saveKey) {
        baseMethods.saveTextValueOfElement(pageName, elementName, saveKey);
    }

    @Step("On the <pageName> page, compare the text of the <elementName> element to the saved value with key <expectedValueKey>")
    public void compareElementTextToSavedValue(String pageName, String elementName, String expectedValueKey) {
        baseMethods.compareElementTextToSavedValue(pageName, elementName, expectedValueKey);
    }
}
