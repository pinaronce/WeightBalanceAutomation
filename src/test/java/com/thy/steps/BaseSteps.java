package com.thy.steps;

import com.thy.methods.BaseMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class BaseSteps {
    private final BaseMethods baseMethods = new BaseMethods();

    @Given("Navigate to {string}")
    @Given("{string} adresine git")
    public void navigateToURL(String url) {
        baseMethods.navigateToURL(url);
    }

    @Then("Verify the current URL is {string}")
    @Then("Mevcut URL'nin {string} olduğu doğrulanır")
    public void verifyURL(String expectedURL) {
        baseMethods.verifyURL(expectedURL);
    }

    @When("Refresh the current page")
    @When("Mevcut sayfayı yenile")
    public void refreshPage() {
        baseMethods.refreshPage();
    }

    @When("Switch to the new tab at index {int}")
    @When("Yeni sekmeye {int} indeksinde geç")
    public void switchToTab(int tabIndex) {
        baseMethods.switchTab(tabIndex);
    }

    @When("Click on {string} page's {string} element")
    @When("{string} sayfasındaki {string} elementine tıklanır")
    public void clickElement(String page, String elementName) {
        baseMethods.click(page, elementName);
    }

    @When("Click on the {string} page's {string} element if it exists")
    @When("{string} sayfasındaki {string} elementine varsa tıklanır")
    public void clickIfElementExists(String page, String elementName) {
        baseMethods.clickIfElementExists(page, elementName);
    }

    @When("Wait {int} seconds")
    @When("{int} saniye bekle")
    public void waitForSeconds(int value) {
        baseMethods.waitBySeconds(value);
    }

    @When("Write {string} to the {string} page's {string} element")
    @When("{string} sayfasındaki {string} elementine {string} yazılır")
    public void enterTextIntoPageElement(String text, String page, String elementName) {
        baseMethods.enterTextIntoElement(text, page, elementName);
    }

    @When("Press enter on {string} page's {string} element")
    @When("{string} sayfasındaki {string} elementine enter tuşuna basılır")
    public void pressEnter(String page, String elementName) {
        baseMethods.pressEnterOnElement(page, elementName);
    }

    @Then("Check if {string} page's {string} element exists")
    @Then("{string} sayfasındaki {string} elementinin var olup olmadığı kontrol edilir")
    public void checkElementExistence(String page, String elementName) {
        baseMethods.checkElementExistence(page, elementName);
    }

    @Then("Verify <page> page's <elementName> element exists, fail with <errorMessage> if absent")
    @Then("<page> sayfasındaki <elementName> elementinin var olduğu doğrulanır, eğer yoksa <errorMessage> ile hata verilir")
    public void checkElementExistenceOrFail(String page, String elementName, String errorMessage) {
        baseMethods.checkElementExistenceOrFail(page, elementName, errorMessage);
    }

    @Then("On the <pageName> page, save the text value of the <elementName> element as <saveKey>")
    @Then("<pageName> sayfasındaki <elementName> elementinin metin değeri <saveKey> olarak kaydedilir")
    public void saveTextValueOfElement(String pageName, String elementName, String saveKey) {
        baseMethods.saveTextValueOfElement(pageName, elementName, saveKey);
    }

    @Then("On the <pageName> page, compare the text of the <elementName> element to the saved value with key <expectedValueKey>")
    @Then("<pageName> sayfasındaki <elementName> elementinin metni, <expectedValueKey> anahtarıyla kaydedilen değerle karşılaştırılır")
    public void compareElementTextToSavedValue(String pageName, String elementName, String expectedValueKey) {
        baseMethods.compareElementTextToSavedValue(pageName, elementName, expectedValueKey);
    }

    @Then("Verify the title of the <page> page is <expectedTitle>")
    @Then("<page> sayfasının başlığının <expectedTitle> olduğu doğrulanır")
    public void verifyPageTitle(String page, String expectedTitle) {
        baseMethods.verifyPageTitle(page, expectedTitle);
    }

    @Then("Verify <page> page's <elementName> element is enabled")
    @Then("<page> sayfasındaki <elementName> elementinin etkin olduğu doğrulanır")
    public void verifyElementIsEnabled(String page, String elementName) {
        baseMethods.verifyElementIsEnabled(page, elementName);
    }

    @Then("Verify <page> page's <elementName> element is disabled")
    @Then("<page> sayfasındaki <elementName> elementinin devre dışı olduğu doğrulanır")
    public void verifyElementIsDisabled(String page, String elementName) {
        baseMethods.verifyElementIsDisabled(page, elementName);
    }

    @Then("Verify the text of <page> page's <elementName> is <expectedText>")
    @Then("<page> sayfasındaki <elementName> elementinin metninin <expectedText> olduğu doğrulanır")
    public void verifyElementText(String page, String elementName, String expectedText) {
        baseMethods.verifyElementText(page, elementName, expectedText);
    }

    @Then("Scroll to the <elementName> element on <page> page")
    @Then("<page> sayfasındaki <elementName> elementine kaydırılır")
    public void scrollToElement(String page, String elementName) {
        baseMethods.scrollToElement(page, elementName);
    }

    @Then("Switch to the <frameName> iFrame")
    @Then("<frameName> iFrame'ine geçilir")
    public void switchToIframe(String frameName) {
        baseMethods.switchToIframe(frameName);
    }

    @Then("Switch out of the iFrame")
    @Then("iFrame'den çıkılır")
    public void switchOutOfIframe() {
        baseMethods.switchOutOfIframe();
    }

    @Then("Verify <page> page's <elementName> element's <attributeName> attribute is <expectedValue>")
    @Then("<page> sayfasındaki <elementName> elementinin <attributeName> niteliğinin <expectedValue> olduğu doğrulanır")
    public void verifyElementAttribute(String page, String elementName, String attributeName, String expectedValue) {
        baseMethods.verifyElementAttribute(page, elementName, attributeName, expectedValue);
    }

    @Then("Select <option> from the <page> page's <dropdown> dropdown")
    @Then("<page> sayfasındaki <dropdown> dropdown'ından <option> seçilir")
    public void selectDropdownOption(String page, String dropdown, String option) {
        baseMethods.selectDropdownOption(page, dropdown, option);
    }

    @Then("Upload <filePath> file on <page> page's <elementName> element")
    @Then("<page> sayfasındaki <elementName> elementine <filePath> dosyası yüklenir")
    public void uploadFile(String page, String elementName, String filePath) {
        baseMethods.uploadFile(page, elementName, filePath);
    }

    @Then("Verify <page> page's <imageName> image is visible")
    @Then("<page> sayfasındaki <imageName> görselinin görünür olduğu doğrulanır")
    public void verifyImageVisibility(String page, String imageName) {
        baseMethods.verifyImageVisibility(page, imageName);
    }
}