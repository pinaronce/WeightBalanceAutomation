package com.thy.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class HomePageElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("login button", By.cssSelector("#sticky-nav > div.StickyNav-desktop-sc-e1b3f420-2.dJsLRW > button"));
    }
}
