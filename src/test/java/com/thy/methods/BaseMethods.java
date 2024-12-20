package com.thy.methods;

import com.thy.base.DriverManager;
import com.thy.elements.LocatorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseMethods extends DriverManager {
    private static final Map<String, String> savedValues = new HashMap<>();
    private static final int DEFAULT_TIMEOUT_SECONDS = 2;
    private static final int DEFAULT_POLLING_INTERVAL_MILLIS = 100;

    private static final Logger logger = LogManager.getLogger(BaseMethods.class);

    public void navigateToURL(String url) {
        try {
            driver.get(url);
            logger.info("Successfully navigated to URL: {} | URL'ye başarıyla gidildi: {}", url, url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: '{}'. Exception: {} | URL'ye gidilemedi: '{}'. Hata: {}",
                    url, e.getMessage(), url, e.getMessage(), e);
            throw e;
        }
    }

    public void refreshPage() {
        try {
            driver.navigate().refresh();
            logger.info("Page refreshed successfully | Sayfa başarıyla yenilendi");
        } catch (Exception e) {
            logger.error("Failed to refresh the page. Exception: {} | Sayfa yenilenemedi. Hata: {}",
                    e.getMessage(), e.getMessage(), e);
            throw e;
        }
    }

    public void switchTab(int tabIndex) {
        try {
            var allWindowHandles = driver.getWindowHandles();
            driver.switchTo().window((String) allWindowHandles.toArray()[tabIndex]);
            logger.info("Successfully switched to tab at index {} | {} indeksli sekmeye başarıyla geçildi",
                    tabIndex, tabIndex);
        } catch (Exception e) {
            logger.error("Failed to switch to tab at index {}. Exception: {} | {} indeksli sekmeye geçilemedi. Hata: {}",
                    tabIndex, e.getMessage(), tabIndex, e.getMessage(), e);
            throw e;
        }
    }

    public void click(String page, String elementName) {
        try {
            logger.info("Attempting to click element '{}' on page '{}' | '{}' sayfasındaki '{}' elementine tıklanmaya çalışılıyor",
                    elementName, page, page, elementName);
            WebElement element = findElement(page, elementName, "clickable");
            element.click();
            logger.info("Successfully clicked element '{}' on page '{}' | '{}' sayfasındaki '{}' elementine başarıyla tıklandı",
                    elementName, page, page, elementName);
        } catch (Exception e) {
            logger.error("Failed to click element '{}' on page '{}'. Error: {} | '{}' sayfasındaki '{}' elementine tıklanamadı. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }

    public void clickIfElementExists(String page, String elementName) {
        try {
            WebElement element = findElement(page, elementName, "clickable");
            element.click();
            logger.info("Successfully clicked on element '{}' on page '{}' | '{}' sayfasındaki '{}' elementine başarıyla tıklandı",
                    elementName, page, page, elementName);
        } catch (NoSuchElementException e) {
            logger.warn("Element '{}' not found on page '{}', skipping click | '{}' sayfasında '{}' elementi bulunamadı, tıklama atlanıyor",
                    elementName, page, page, elementName);
        } catch (Exception e) {
            logger.error("Error occurred while trying to click element '{}' on page '{}'. Exception: {} | '{}' sayfasındaki '{}' elementine tıklanırken hata oluştu. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage(), e);
        }
    }

    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.info("Waited for {} seconds | {} saniye beklendi", seconds, seconds);
        } catch (InterruptedException e) {
            logger.error("Error while waiting for {} seconds. Exception: {} | {} saniye beklenirken hata oluştu. Hata: {}",
                    seconds, e.getMessage(), seconds, e.getMessage(), e);
        }
    }

    public void enterTextIntoElement(String text, String page, String elementName) {
        try {
            logger.info("Attempting to enter text '{}' into element '{}' on page '{}' | '{}' sayfasındaki '{}' elementine '{}' metni girilmeye çalışılıyor",
                    text, elementName, page, page, elementName, text);
            WebElement element = findElement(page, elementName, "visible");
            element.clear();
            element.sendKeys(text);
            logger.info("Successfully entered text '{}' into element '{}' on page '{}' | '{}' sayfasındaki '{}' elementine '{}' metni başarıyla girildi",
                    text, elementName, page, page, elementName, text);
        } catch (Exception e) {
            logger.error("Failed to enter text '{}' into element '{}' on page '{}'. Error: {} | '{}' sayfasındaki '{}' elementine '{}' metni girilemedi. Hata: {}",
                    text, elementName, page, e.getMessage(), page, elementName, text, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }

    public void pressEnterOnElement(String page, String elementName) {
        try {
            WebElement element = findElement(page, elementName, "visible");
            element.sendKeys(Keys.RETURN);
            logger.info("Pressed Enter on element '{}' on page '{}' | '{}' sayfasındaki '{}' elementine Enter tuşuna basıldı",
                    elementName, page, page, elementName);
        } catch (Exception e) {
            logger.error("Failed to press Enter on element '{}' on page '{}'. Exception: {} | '{}' sayfasındaki '{}' elementine Enter tuşuna basılamadı. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage(), e);
            throw e;
        }
    }

    public void checkElementExistenceOrFail(String page, String elementName, String errorMessage) {
        try {
            WebElement element = findElement(page, elementName, "visible");
            if (element == null) {
                logger.error("Element '{}' not found on page '{}'. Error: {} | '{}' sayfasında '{}' elementi bulunamadı. Hata: {}",
                        elementName, page, errorMessage, page, elementName, errorMessage);
                throw new NoSuchElementException(errorMessage);
            }
        } catch (Exception e) {
            logger.error("Error while checking existence of element '{}' on page '{}'. Exception: {} | '{}' sayfasındaki '{}' elementinin varlığı kontrol edilirken hata oluştu. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage(), e);
            throw e;
        }
    }

    public void verifyElementTextIgnoreCase(String page, String elementName, String expectedText) {
        try {
            logger.info("Verifying text of element '{}' on page '{}' against expected text '{}' | '{}' sayfasındaki '{}' elementinin metni '{}' ile karşılaştırılıyor",
                    elementName, page, expectedText, page, elementName, expectedText);
            String actualText = getElementText(page, elementName);
            assertTextIgnoreCase(actualText, expectedText);
        } catch (Exception e) {
            logger.error("Failed to verify text for element '{}' on page '{}'. Exception: {} | '{}' sayfasındaki '{}' elementinin metni doğrulanamadı. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage(), e);
            throw e;
        }
    }

    public void compareElementTextToSavedValue(String page, String elementName, String expectedValueKey) {
        try {
            logger.info("Comparing text of element '{}' on page '{}' to saved value with key '{}' | '{}' sayfasındaki '{}' elementinin metni '{}' anahtarındaki değer ile karşılaştırılıyor",
                    elementName, page, expectedValueKey, page, elementName, expectedValueKey);
            String actualText = getElementText(page, elementName);
            String expectedValue = getStoredValue(expectedValueKey);
            assertTextContains(actualText, expectedValue, expectedValueKey);
        } catch (Exception e) {
            logger.error("Failed to compare text for element '{}' on page '{}'. Exception: {} | '{}' sayfasındaki '{}' elementinin metni karşılaştırılamadı. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage(), e);
            throw e;
        }
    }

    public String getElementText(String page, String elementName) {
        try {
            WebElement element = findElement(page, elementName, "visible");
            logger.info("Getting text from element '{}' on page '{}' | '{}' sayfasındaki '{}' elementinin metni alınıyor",
                    elementName, page, page, elementName);
            return element.getText().trim();
        } catch (Exception e) {
            logger.error("Failed to get text of element '{}' on page '{}'. Exception: {} | '{}' sayfasındaki '{}' elementinin metni alınamadı. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage(), e);
            throw e;
        }
    }

    public String getStoredValue(String key) {
        return savedValues.get(key);
    }

    public void assertTextIgnoreCase(String actual, String expected) {
        try {
            Assertions.assertTrue(actual.equalsIgnoreCase(expected),
                    "Expected text: '" + expected + "' does not match actual text: '" + actual + "'.");
            logger.info("Text verification passed. Expected: '{}' matches actual: '{}' | Metin doğrulaması başarılı. Beklenen: '{}' gerçek değer: '{}'",
                    expected, actual, expected, actual);
        } catch (AssertionError e) {
            logger.error("Text verification failed. Expected: '{}' but got: '{}' | Metin doğrulaması başarısız. Beklenen: '{}' alınan: '{}'",
                    expected, actual, expected, actual);
            throw e;
        }
    }

    public void assertTextContains(String actual, String expected, String key) {
        try {
            Assertions.assertTrue(actual.contains(expected),
                    "Expected text: '" + expected + "' associated with '" + key + "' does not match the actual text: '" + actual + "'.");
            logger.info("Text contains verification passed for '{}' | '{}' için metin içerme doğrulaması başarılı", key, key);
        } catch (AssertionError e) {
            logger.error("Text contains verification failed for '{}'. Expected: '{}' but actual text: '{}' | '{}' için metin içerme doğrulaması başarısız. Beklenen: '{}' gerçek metin: '{}'",
                    key, expected, actual, key, expected, actual);
            throw e;
        }
    }

    public void checkElementExistence(String page, String elementName) {
        try {
            By locator = LocatorRepository.getLocator(page, elementName);
            driver.findElement(locator);
            logger.info("Element '{}' exists on page '{}' | '{}' sayfasında '{}' elementi mevcut",
                    elementName, page, page, elementName);
        } catch (NoSuchElementException e) {
            logger.error("Element '{}' not found on page '{}' | '{}' sayfasında '{}' elementi bulunamadı",
                    elementName, page, page, elementName);
            throw e;
        }
    }

    public void saveTextValueOfElement(String page, String elementName, String saveKey) {
        try {
            By locator = LocatorRepository.getLocator(page, elementName);
            WebElement element = driver.findElement(locator);
            savedValues.put(saveKey, element.getText());
            logger.info("Saved text value of element '{}' from page '{}' with key '{}' | '{}' sayfasındaki '{}' elementinin metni '{}' anahtarı ile kaydedildi",
                    elementName, page, saveKey, page, elementName, saveKey);
        } catch (Exception e) {
            logger.error("Failed to save text value of element '{}' from page '{}'. Exception: {} | '{}' sayfasındaki '{}' elementinin metni kaydedilemedi. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage(), e);
            throw e;
        }
    }

    public void verifyPageTitle(String page, String expectedTitle) {
        try {
            logger.info("Attempting to verify page title for page '{}'. Expected title: '{}' | '{}' sayfasının başlığı doğrulanıyor. Beklenen başlık: '{}'",
                    page, expectedTitle, page, expectedTitle);
            String actualTitle = driver.getTitle();
            if (actualTitle != null && actualTitle.equals(expectedTitle)) {
                logger.info("Page title verification successful. Page '{}' has expected title: '{}' | Sayfa başlığı doğrulaması başarılı. '{}' sayfasının başlığı beklenen değerde: '{}'",
                        page, expectedTitle, page, expectedTitle);
            } else {
                logger.error("Page title verification failed for page '{}'. Expected: '{}', but got: '{}' | '{}' sayfasının başlık doğrulaması başarısız. Beklenen: '{}', alınan: '{}'",
                        page, expectedTitle, actualTitle, page, expectedTitle, actualTitle);
                throw new AssertionError("Expected title: " + expectedTitle + " but got: " + actualTitle + " on page: " + page);
            }
        } catch (Exception e) {
            logger.error("Failed to verify page title for page '{}'. Error: {} | '{}' sayfasının başlığı doğrulanamadı. Hata: {}",
                    page, e.getMessage(), page, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }

    public void verifyElementIsEnabled(String page, String elementName) {
        try {
            logger.info("Attempting to verify element '{}' on page '{}' is enabled", elementName, page);
            WebElement element = findElement(page, elementName, "visible");
            if (element.isEnabled()) {
                logger.info("Element '{}' on page '{}' is enabled as expected", elementName, page);
            } else {
                logger.error("Element '{}' on page '{}' is not enabled when it should be", elementName, page);
                throw new AssertionError("Element " + elementName + " on " + page + " is not enabled.");
            }
        } catch (Exception e) {
            logger.error("Failed to verify if element '{}' on page '{}' is enabled. Error: {}",
                    elementName, page, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }

    public void verifyElementIsDisabled(String page, String elementName) {
        try {
            logger.info("Attempting to verify element '{}' on page '{}' is disabled", elementName, page);
            WebElement element = findElement(page, elementName, "visible");
            if (!element.isEnabled()) {
                logger.info("Element '{}' on page '{}' is disabled as expected", elementName, page);
            } else {
                logger.error("Element '{}' on page '{}' is enabled when it should be disabled", elementName, page);
                throw new AssertionError("Element " + elementName + " on " + page + " is enabled when it should be disabled.");
            }
        } catch (Exception e) {
            logger.error("Failed to verify if element '{}' on page '{}' is disabled. Error: {}",
                    elementName, page, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }

    public void verifyElementText(String page, String elementName, String expectedText) {
        try {
            logger.info("Attempting to verify text of element '{}' on page '{}'. Expected text: '{}'", elementName, page, expectedText);
            WebElement element = findElement(page, elementName, "visible");
            String actualText = element.getText();
            if (actualText.equals(expectedText)) {
                logger.info("Text verification successful. Element '{}' on page '{}' contains expected text: '{}'",
                        elementName, page, expectedText);
            } else {
                logger.error("Text verification failed. Element '{}' on page '{}'. Expected: '{}', but got: '{}'",
                        elementName, page, expectedText, actualText);
                throw new AssertionError("Expected text: " + expectedText + " but got: " + actualText);
            }
        } catch (Exception e) {
            logger.error("Failed to verify text of element '{}' on page '{}'. Error: {}", elementName, page, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }

    public void scrollToElement(String page, String elementName) {
        WebElement element = findElement(page, elementName, "visible");  // Add "visible" condition
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void switchToIframe(String frameName) {
        WebElement iframe = driver.findElement(By.name(frameName)); // or use By.id, By.cssSelector, etc.
        driver.switchTo().frame(iframe);
    }

    public void switchOutOfIframe() {
        driver.switchTo().defaultContent();
    }

    public void verifyElementAttribute(String page, String elementName, String attributeName, String expectedValue) {
        WebElement element = findElement(page, elementName, "visible");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualValue = (String) js.executeScript("return arguments[0].getAttribute(arguments[1]);", element, attributeName);
        if (actualValue == null || !actualValue.equals(expectedValue)) {
            throw new AssertionError("Expected attribute value: " + expectedValue + " but got: " + actualValue);
        }
    }


    public void verifyURL(String expectedURL) {
        try {
            logger.info("Attempting to verify current URL. Expected URL: '{}' | Mevcut URL doğrulanıyor. Beklenen URL: '{}'",
                    expectedURL, expectedURL);
            String currentURL = driver.getCurrentUrl();
            if (currentURL.equals(expectedURL)) {
                logger.info("URL verification successful. Current URL matches expected URL: '{}' | URL doğrulaması başarılı. Mevcut URL beklenen URL ile eşleşiyor: '{}'",
                        expectedURL, expectedURL);
            } else {
                logger.error("URL verification failed. Expected: '{}', but got: '{}' | URL doğrulaması başarısız. Beklenen: '{}', alınan: '{}'",
                        expectedURL, currentURL, expectedURL, currentURL);
                throw new AssertionError("Expected URL: " + expectedURL + " but got: " + currentURL);
            }
        } catch (Exception e) {
            logger.error("Failed to verify URL. Error: {} | URL doğrulanamadı. Hata: {}",
                    e.getMessage(), e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }


    public void selectDropdownOption(String page, String dropdown, String option) {
        try {
            logger.info("Attempting to select option '{}' from dropdown '{}' on page '{}' | '{}' sayfasındaki '{}' açılır menüsünden '{}' seçeneği seçilmeye çalışılıyor",
                    option, dropdown, page, page, dropdown, option);
            WebElement dropdownElement = findElement(page, dropdown, "visible");
            Select select = new Select(dropdownElement);
            select.selectByVisibleText(option);
            logger.info("Successfully selected option '{}' from dropdown '{}' on page '{}' | '{}' sayfasındaki '{}' açılır menüsünden '{}' seçeneği başarıyla seçildi",
                    option, dropdown, page, page, dropdown, option);
        } catch (Exception e) {
            logger.error("Failed to select option '{}' from dropdown '{}' on page '{}'. Error: {} | '{}' sayfasındaki '{}' açılır menüsünden '{}' seçeneği seçilemedi. Hata: {}",
                    option, dropdown, page, e.getMessage(), page, dropdown, option, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }

    public void uploadFile(String page, String elementName, String filePath) {
        try {
            logger.info("Attempting to upload file '{}' using element '{}' on page '{}' | '{}' sayfasındaki '{}' elementi kullanılarak '{}' dosyası yüklenmeye çalışılıyor",
                    filePath, elementName, page, page, elementName, filePath);
            WebElement fileInput = findElement(page, elementName, "visible");
            fileInput.sendKeys(filePath);
            logger.info("Successfully uploaded file '{}' using element '{}' on page '{}' | '{}' sayfasındaki '{}' elementi kullanılarak '{}' dosyası başarıyla yüklendi",
                    filePath, elementName, page, page, elementName, filePath);
        } catch (Exception e) {
            logger.error("Failed to upload file '{}' using element '{}' on page '{}'. Error: {} | '{}' sayfasındaki '{}' elementi kullanılarak '{}' dosyası yüklenemedi. Hata: {}",
                    filePath, elementName, page, e.getMessage(), page, elementName, filePath, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }

    public void verifyImageVisibility(String page, String elementName) {
        try {
            logger.info("Attempting to verify visibility of image '{}' on page '{}' | '{}' sayfasındaki '{}' görselinin görünürlüğü kontrol ediliyor",
                    elementName, page, page, elementName);
            WebElement element = findElement(page, elementName, "visible");
            if (element.isDisplayed()) {
                logger.info("Image '{}' on page '{}' is visible as expected | '{}' sayfasındaki '{}' görseli beklendiği gibi görünür durumda",
                        elementName, page, page, elementName);
            } else {
                logger.error("Image '{}' on page '{}' is not visible when it should be | '{}' sayfasındaki '{}' görseli görünür olması gerekirken görünmüyor",
                        elementName, page, page, elementName);
                throw new AssertionError("Image " + elementName + " on " + page + " is not visible.");
            }
        } catch (Exception e) {
            logger.error("Failed to verify visibility of image '{}' on page '{}'. Error: {} | '{}' sayfasındaki '{}' görselinin görünürlüğü doğrulanamadı. Hata: {}",
                    elementName, page, e.getMessage(), page, elementName, e.getMessage());
            logger.debug("Stack trace:", e);
            throw e;
        }
    }


    private WebElement waitForElement(String page, String elementName, String condition) {
        By locator = getLocatorFromPage(page, elementName);

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofMillis(DEFAULT_POLLING_INTERVAL_MILLIS))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        try {
            WebElement element = wait.until(driver -> {
                if ("clickable".equals(condition)) {
                    return ExpectedConditions.elementToBeClickable(locator).apply(driver);
                } else if ("visible".equals(condition)) {
                    return ExpectedConditions.visibilityOfElementLocated(locator).apply(driver);
                }
                return null;
            });
            logger.info("Element '{}' on page '{}' found and is '{}' | '{}' sayfasındaki '{}' elementi bulundu ve '{}' durumunda",
                    elementName, page, condition, page, elementName, condition);
            return element;
        } catch (Exception e) {
            logger.error("Failed to wait for element '{}' with condition '{}' on page '{}'. Exception: {} | '{}' sayfasındaki '{}' elementi '{}' durumunda beklenemedi. Hata: {}",
                    elementName, condition, page, e.getMessage(), page, elementName, condition, e.getMessage(), e);
            throw e;
        }
    }

    private WebElement findElement(String page, String elementName, String condition) {
        return waitForElement(page, elementName, condition);
    }

    private By getLocatorFromPage(String page, String elementName) {
        return LocatorRepository.getLocator(page, elementName);
    }
}