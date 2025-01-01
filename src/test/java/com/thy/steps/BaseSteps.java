package com.thy.steps;

import com.thy.methods.BaseMethods;
import com.thy.base.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class BaseSteps {
    private final BaseMethods baseMethods;
    private final DriverManager driverManager;

    public BaseSteps() {
        this.baseMethods = new BaseMethods();
        this.driverManager = new DriverManager();
    }

    @Given("Navigate to {string}")
    @Given("{string} adresi ziyaret edilir")
    public void navigateToURL(String url) {
        driverManager.getDriver().get(url);
    }

    @When("Click on {string} page {string} element")
    @When("{string} sayfasındaki {string} elementine tıklanır")
    public void clickElement(String page, String elementName) {
        baseMethods.click(page, elementName);
    }

    @Then("Verify the current URL is {string}")
    @Then("Mevcut URL'nin {string} olduğu doğrulanır")
    public void verifyURL(String expectedURL) {
        baseMethods.verifyURL(expectedURL);
    }

    @When("Click if element exists on {string} page {string} element")
    @When("{string} sayfasındaki {string} elementi varsa tıklanır")
    public void clickIfElementExists(String page, String elementName) {
        baseMethods.clickIfElementExists(page, elementName);
    }

    @When("Wait {int} seconds")
    @When("{int} saniye bekle")
    public void waitBySeconds(int seconds) {
        baseMethods.waitBySeconds(seconds);
    }

    @When("Enter {string} text to {string} page's {string} element")
    @When("{string} sayfasındaki {string} elementine {string} yazılır")
    public void enterText(String text, String page, String elementName) {
        baseMethods.enterTextIntoElement(text, page, elementName);
    }

    @When("Press enter key on {string} page's {string} element")
    @When("{string} sayfasındaki {string} elementine enter tuşuna basılır")
    public void pressEnter(String page, String elementName) {
        baseMethods.pressEnterOnElement(page, elementName);
    }

    @Then("Check if element exists on {string} page's {string} element with error message {string}")
    @Then("{string} sayfasındaki {string} elementi {string} hata mesajı ile kontrol edilir")
    public void checkElementExistence(String page, String elementName, String errorMessage) {
        baseMethods.checkElementExistenceOrFail(page, elementName, errorMessage);
    }

    @Then("Verify text of {string} page's {string} element is {string}")
    @Then("{string} sayfasındaki {string} elementinin metni {string} ile aynı olduğu doğrulanır")
    public void verifyElementText(String page, String elementName, String expectedText) {
        baseMethods.verifyElementTextIgnoreCase(page, elementName, expectedText);
    }

    @Then("Save text of {string} page's {string} element with key {string}")
    @Then("{string} sayfasındaki {string} elementinin metni {string} anahtarı ile kaydedilir")
    public void saveElementText(String page, String elementName, String key) {
        baseMethods.saveTextValueOfElement(page, elementName, key);
    }

    @Then("Compare text of {string} page's {string} element with saved value {string}")
    @Then("{string} sayfasındaki {string} elementinin metni {string} değeri ile karşılaştırılır")
    public void compareElementText(String page, String elementName, String key) {
        baseMethods.compareElementTextToSavedValue(page, elementName, key);
    }

    @Then("Verify page title is {string} on {string} page")
    @Then("{string} sayfasının başlığının {string} olduğu doğrulanır")
    public void verifyPageTitle(String page, String expectedTitle) {
        baseMethods.verifyPageTitle(page, expectedTitle);
    }

    @When("Scroll to {string} page's {string} element")
    @When("{string} sayfasındaki {string} elementine scroll edilir")
    public void scrollToElement(String page, String elementName) {
        baseMethods.scrollToElement(page, elementName);
    }

    @When("Switch to iframe {string}")
    @When("{string} iframe'ine geçiş yapılır")
    public void switchToIframe(String frameName) {
        baseMethods.switchToIframe(frameName);
    }

    @When("Switch out of iframe")
    @When("iframe'den çıkılır")
    public void switchOutOfIframe() {
        baseMethods.switchOutOfIframe();
    }

    @Then("Verify {string} page's {string} element's {string} attribute is {string}")
    @Then("{string} sayfasındaki {string} elementinin {string} niteliği {string} olduğu doğrulanır")
    public void verifyElementAttribute(String page, String elementName, String attributeName, String expectedValue) {
        baseMethods.verifyElementAttribute(page, elementName, attributeName, expectedValue);
    }

    @When("Select {string} option from {string} page's {string} dropdown")
    @When("{string} sayfasındaki {string} dropdown'ından {string} seçeneği seçilir")
    public void selectDropdownOption(String option, String page, String dropdown) {
        baseMethods.selectDropdownOption(page, dropdown, option);
    }

    @When("Upload file {string} to {string} page's {string} element")
    @When("{string} dosyası {string} sayfasındaki {string} elementine yüklenir")
    public void uploadFile(String filePath, String page, String elementName) {
        baseMethods.uploadFile(page, elementName, filePath);
    }

    @Then("Verify image is visible on {string} page's {string} element")
    @Then("{string} sayfasındaki {string} görselinin görünür olduğu doğrulanır")
    public void verifyImageVisibility(String page, String elementName) {
        baseMethods.verifyImageVisibility(page, elementName);
    }

    @Then("Verify element is enabled on {string} page's {string} element")
    @Then("{string} sayfasındaki {string} elementinin aktif olduğu doğrulanır")
    public void verifyElementIsEnabled(String page, String elementName) {
        baseMethods.verifyElementIsEnabled(page, elementName);
    }

    @Then("Verify element is disabled on {string} page's {string} element")
    @Then("{string} sayfasındaki {string} elementinin devre dışı olduğu doğrulanır")
    public void verifyElementIsDisabled(String page, String elementName) {
        baseMethods.verifyElementIsDisabled(page, elementName);
    }

    @When("Switch to tab {int}")
    @When("{int} numaralı sekmeye geçilir")
    public void switchTab(int tabIndex) {
        baseMethods.switchTab(tabIndex);
    }

    @When("Refresh page")
    @When("Sayfa yenilenir")
    public void refreshPage() {
        baseMethods.refreshPage();
    }
}
