package com.appiumtest;

import com.appiumtest.app.core.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    @BeforeAll
    void setUp() {
        DriverFactory.runAppiumServer();
        DriverFactory.getDriver();
    }

    @AfterAll
    void tearDown() {
        DriverFactory.killDriver();
        DriverFactory.stopAppiumServer();
    }
}