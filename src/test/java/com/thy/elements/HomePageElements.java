package com.thy.elements;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class HomePageElements {
    public static final Map<String, By> LOCATORS = new HashMap<>();

    static {
        LOCATORS.put("buy and manage tickets button", By.cssSelector("ul.nav.navbar-nav.flex-row.mega-menu-main.mr-5.d-none.d-md-flex.flex-for-desktop.float-end li:nth-child(1)>a"));
    }
}
