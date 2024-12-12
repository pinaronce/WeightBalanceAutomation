package com.thy.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class LocatorRepository {
    private static Map<String, Map<String, By>> pageElementLocators = new HashMap<>();

    static {
        pageElementLocators.put(" home", HomePageElements.LOCATORS);

    }

    public static Map<String, Map<String, By>> getPageElementLocators() {
        return pageElementLocators;
    }
}