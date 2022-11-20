package com.appiumtest;

import com.appiumtest.app.core.DriverFactory;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    @Test
    void name() {
        AndroidDriver driver = DriverFactory.getDriver();
        WebElement username = driver
                .findElement(AppiumBy.id("container"))
                .findElement(AppiumBy.id("username"));
        username.sendKeys("By123456!");
        Assertions.assertEquals("By123456!", username.getText());
    }
}