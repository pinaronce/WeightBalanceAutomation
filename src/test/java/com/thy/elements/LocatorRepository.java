package com.thy.elements;

import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocatorRepository {
    private static final Logger logger = LogManager.getLogger(LocatorRepository.class);
    private static final Map<String, Map<String, By>> elementRepository = new HashMap<>();

    static {
        HomePageElements.LOCATORS.forEach((element, locator) -> addLocator("homepage", element, locator));
    }

    public static By getLocator(String page, String element) {
        Map<String, By> pageElements = elementRepository.get(page.toLowerCase());
        if (pageElements == null || !pageElements.containsKey(element.toLowerCase())) {
            String errorMessage = String.format("Element '%s' not found on page '%s'", element, page);
            logger.error(errorMessage + " | '%s' sayfasında '%s' elementi bulunamadı", element, page);
            throw new IllegalArgumentException(errorMessage);
        }
        return pageElements.get(element.toLowerCase());
    }

    public static void addLocator(String page, String element, By locator) {
        elementRepository
                .computeIfAbsent(page.toLowerCase(), k -> new HashMap<>())
                .put(element.toLowerCase(), locator);

        logger.info("Locator added for element '{}' on page '{}' | '{}' sayfasına '{}' elementi için locator eklendi",
                element, page, page, element);
    }
}
