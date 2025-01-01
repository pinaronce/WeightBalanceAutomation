package com.thy.hooks;

import com.thy.base.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private final DriverManager driverManager;

    public Hooks() {
        this.driverManager = new DriverManager();
    }

    @Before
    public void setUp(Scenario scenario) {
        driverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Screenshot alınabilir
        }
        driverManager.cleanUpTestEnvironment();
    }
}