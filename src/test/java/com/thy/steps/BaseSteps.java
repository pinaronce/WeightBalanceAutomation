package com.thy.steps;

import com.thoughtworks.gauge.Step;
import com.thy.methods.BaseMethods;

public class BaseSteps {
    private final BaseMethods baseMethods = new BaseMethods();

    @Step({"Navigate to the <url>",
            "<url> adresi ziyaret edilir"})
    public void navigateToURL(String url) {
        baseMethods.navigateToURL(url);
    }

    @Step({"Verify the current URL is <expectedURL>",
            "Mevcut URL'nin <expectedURL> olduğu doğrulanır"})
    public void verifyURL(String expectedURL) {
        baseMethods.verifyURL(expectedURL);
    }

    @Step({"Refresh the current page",
            "Mevcut sayfayı yenile"})
    public void refreshPage() {
        baseMethods.refreshPage();
    }

    @Step({"Switch to the new tab at index <tabIndex>",
            "Yeni sekmeye <tabIndex> indeksinde geç"})
    public void switchToTab(int tabIndex) {
        baseMethods.switchTab(tabIndex);
    }

    @Step({"Click on <page> page's <elementName> element",
            "<page> sayfasındaki <elementName> elementine tıklanır"})
    public void clickElement(String page, String elementName) {
        baseMethods.click(page, elementName);
    }

    @Step({"Click on the <page> page's <elementName> element if it exists",
            "<page> sayfasındaki <elementName> elementine varsa tıklanır"})
    public void clickIfElementExists(String page, String elementName) {
        baseMethods.clickIfElementExists(page, elementName);
    }

    @Step({"Wait <value> seconds",
            "<value> saniye bekle"})
    public void waitForSeconds(int value) {
        baseMethods.waitBySeconds(value);
    }

    @Step({"Write <text> to the <page> page's <elementName> element",
            "<page> sayfasındaki <elementName> elementine <text> yazılır"})
    public void enterTextIntoPageElement(String text, String page, String elementName) {
        baseMethods.enterTextIntoElement(text, page, elementName);
    }

    @Step({"Press enter on <page> page's <elementName> element",
            "<page> sayfasındaki <elementName> elementine enter tuşuna basılır"})
    public void pressEnter(String page, String elementName) {
        baseMethods.pressEnterOnElement(page, elementName);
    }

    @Step({"Check if <page> page's <elementName> element exists",
            "<page> sayfasındaki <elementName> elementinin var olup olmadığı kontrol edilir"})
    public void checkElementExistence(String page, String elementName) {
        baseMethods.checkElementExistence(page, elementName);
    }

    @Step({"Verify <page> page's <elementName> element exists, fail with <errorMessage> if absent",
            "<page> sayfasındaki <elementName> elementinin var olduğu doğrulanır, eğer yoksa <errorMessage> ile hata verilir"})
    public void checkElementExistenceOrFail(String page, String elementName, String errorMessage) {
        baseMethods.checkElementExistenceOrFail(page, elementName, errorMessage);
    }

    @Step({"Verify that the text of <page> page's <elementName> element is <expectedText>",
            "<page> sayfasındaki <elementName> elementinin metninin <expectedText> olduğu doğrulanır"})
    public void verifyElementTextIgnoreCase(String page, String elementName, String expectedText) {
        baseMethods.verifyElementTextIgnoreCase(page, elementName, expectedText);
    }

    @Step({"On the <pageName> page, save the text value of the <elementName> element as <saveKey>",
            "<pageName> sayfasındaki <elementName> elementinin metin değeri <saveKey> olarak kaydedilir"})
    public void saveTextValueOfElement(String pageName, String elementName, String saveKey) {
        baseMethods.saveTextValueOfElement(pageName, elementName, saveKey);
    }

    @Step({"On the <pageName> page, compare the text of the <elementName> element to the saved value with key <expectedValueKey>",
            "<pageName> sayfasındaki <elementName> elementinin metni, <expectedValueKey> anahtarıyla kaydedilen değerle karşılaştırılır"})
    public void compareElementTextToSavedValue(String pageName, String elementName, String expectedValueKey) {
        baseMethods.compareElementTextToSavedValue(pageName, elementName, expectedValueKey);
    }

    @Step({"Verify the title of the <page> page is <expectedTitle>",
            "<page> sayfasının başlığının <expectedTitle> olduğu doğrulanır"})
    public void verifyPageTitle(String page, String expectedTitle) {
        baseMethods.verifyPageTitle(page, expectedTitle);
    }

    @Step({"Verify <page> page's <elementName> element is enabled",
            "<page> sayfasındaki <elementName> elementinin etkin olduğu doğrulanır"})
    public void verifyElementIsEnabled(String page, String elementName) {
        baseMethods.verifyElementIsEnabled(page, elementName);
    }
    @Step({"Verify <page> page's <elementName> element is disabled",
            "<page> sayfasındaki <elementName> elementinin devre dışı olduğu doğrulanır"})
    public void verifyElementIsDisabled(String page, String elementName) {
        baseMethods.verifyElementIsDisabled(page, elementName);
    }
    @Step({"Verify the text of <page> page's <elementName> is <expectedText>",
            "<page> sayfasındaki <elementName> elementinin metninin <expectedText> olduğu doğrulanır"})
    public void verifyElementText(String page, String elementName, String expectedText) {
        baseMethods.verifyElementText(page, elementName, expectedText);
    }
    @Step({"Scroll to the <elementName> element on <page> page",
            "<page> sayfasındaki <elementName> elementine kaydırılır"})
    public void scrollToElement(String page, String elementName) {
        baseMethods.scrollToElement(page, elementName);
    }
    @Step({"Switch to the <frameName> iFrame",
            "<frameName> iFrame'ine geçilir"})
    public void switchToIframe(String frameName) {
        baseMethods.switchToIframe(frameName);
    }
    @Step({"Switch out of the iFrame",
            "iFrame'den çıkılır"})
    public void switchOutOfIframe() {
        baseMethods.switchOutOfIframe();
    }
    @Step({"Verify <page> page's <elementName> element's <attributeName> attribute is <expectedValue>",
            "<page> sayfasındaki <elementName> elementinin <attributeName> niteliğinin <expectedValue> olduğu doğrulanır"})
    public void verifyElementAttribute(String page, String elementName, String attributeName, String expectedValue) {
        baseMethods.verifyElementAttribute(page, elementName, attributeName, expectedValue);
    }

    @Step({"Select <option> from the <page> page's <dropdown> dropdown",
            "<page> sayfasındaki <dropdown> dropdown'ından <option> seçilir"})
    public void selectDropdownOption(String page, String dropdown, String option) {
        baseMethods.selectDropdownOption(page, dropdown, option);
    }
    @Step({"Upload <filePath> file on <page> page's <elementName> element",
            "<page> sayfasındaki <elementName> elementine <filePath> dosyası yüklenir"})
    public void uploadFile(String page, String elementName, String filePath) {
        baseMethods.uploadFile(page, elementName, filePath);
    }
    @Step({"Verify <page> page's <imageName> image is visible",
            "<page> sayfasındaki <imageName> görselinin görünür olduğu doğrulanır"})
    public void verifyImageVisibility(String page, String imageName) {
        baseMethods.verifyImageVisibility(page, imageName);
    }

}
