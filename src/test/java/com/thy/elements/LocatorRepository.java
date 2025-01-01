package com.thy.elements;

import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class LocatorRepository {
    private static final Map<String, Map<String, By>> PAGE_ELEMENTS = new HashMap<>();

    static {
        // Homepage elements
        Map<String, By> homepageElements = new HashMap<>();
        homepageElements.put("login_button", By.id("login-button"));
        // ... diğer homepage elementleri

        // Login page elements
        Map<String, By> loginPageElements = new HashMap<>();
        loginPageElements.put("username_input", By.id("username"));
        loginPageElements.put("password_input", By.id("password"));
        // ... diğer login page elementleri

        // Add pages to main map
        PAGE_ELEMENTS.put("homepage", homepageElements);
        PAGE_ELEMENTS.put("login_page", loginPageElements);
        // ... diğer sayfalar
    }

    public static By getLocator(String page, String elementName) {
        Map<String, By> pageElements = PAGE_ELEMENTS.get(page.toLowerCase());
        if (pageElements == null) {
            throw new IllegalArgumentException("Page not found: " + page);
        }

        By locator = pageElements.get(elementName.toLowerCase());
        if (locator == null) {
            throw new IllegalArgumentException(
                String.format("Element '%s' not found on page '%s'", elementName, page)
            );
        }

        return locator;
    }

    public static void addLocator(String page, String elementName, By locator) {
        PAGE_ELEMENTS.computeIfAbsent(page.toLowerCase(), k -> new HashMap<>())
                    .put(elementName.toLowerCase(), locator);
    }
}
