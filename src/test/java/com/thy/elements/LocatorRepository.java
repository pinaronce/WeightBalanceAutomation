package com.thy.elements;

import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class LocatorRepository {
    private static final Map<String, Map<String, By>> PAGE_ELEMENTS = new HashMap<>();

    static {
        // Ana sayfa elementleri
        Map<String, By> homePageElements = new HashMap<>();
        homePageElements.put("loginButton", By.id("login-button"));
        homePageElements.put("username", By.id("username"));
        homePageElements.put("password", By.id("password"));
        PAGE_ELEMENTS.put("home", homePageElements);

        // Diğer sayfalar için elementler
        // ...
    }

    public static By getLocator(String page, String element) {
        Map<String, By> pageElements = PAGE_ELEMENTS.get(page.toLowerCase());
        if (pageElements == null) {
            throw new IllegalArgumentException("Page not found: " + page);
        }
        By locator = pageElements.get(element.toLowerCase());
        if (locator == null) {
            throw new IllegalArgumentException(
                String.format("Element '%s' not found on page '%s'", element, page));
        }
        return locator;
    }

    public static void addLocator(String page, String element, By locator) {
        PAGE_ELEMENTS.computeIfAbsent(page.toLowerCase(), k -> new HashMap<>())
            .put(element.toLowerCase(), locator);
    }
}
