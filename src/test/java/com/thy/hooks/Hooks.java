package com.thy.hooks;

import com.thy.base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static final DriverManager driverManager = new DriverManager();

    @Before
    public void setUp() {
        WebDriver driver = driverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = driverManager.getDriver();
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        driverManager.cleanUpTestEnvironment();
    }
}