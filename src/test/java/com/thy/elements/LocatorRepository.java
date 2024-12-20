package com.thy.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class LocatorRepository {
    private static final Map<String, Map<String, By>> pageElementLocators = new HashMap<>();

    static {
        pageElementLocators.put("home", HomePageElements.LOCATORS);
    }

    public static By getLocator(String page, String elementName) {
        Map<String, By> elementLocators = pageElementLocators.get(page);
        if (elementLocators == null) {
            throw new IllegalArgumentException("No locators found for page: " + page);
        }
        By locator = elementLocators.get(elementName);
        if (locator == null) {
            throw new IllegalArgumentException("No locator found for element: " + elementName + " on page: " + page);
        }
        return locator;
    }
}
