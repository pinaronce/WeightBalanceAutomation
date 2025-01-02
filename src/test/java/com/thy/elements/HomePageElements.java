package com.thy.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class HomePageElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("downloads link", By.xpath("//a[contains(text(),'Downloads')]"));
        LOCATORS.put("downloads text", By.xpath("//h1[contains(text(),'Downloads')]"));
    }
}
