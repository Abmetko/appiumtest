package com.appiumtest.app.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static final String APP_DIR = "src/main/resources";
    private static final String APP_NAME = "app-debug.apk";
    private static final String APP_FILE_ABSOLUTE_PATH = getAppFilePath();
    private static final String APPIUM_SERVER_URL = "127.0.0.1";
    private static final String APPIUM_SERVER_PATH = "/wd/hub/";
    private static final String APPIUM_SERVER_PORT = "4723";

    private static AndroidDriver driver = null;
    private static AppiumDriverLocalService service = null;

    private DriverFactory() {
    }

    public static AndroidDriver getDriver() {
        if (driver == null) {
            configureDriver();
        }
        return driver;
    }

    private static void configureDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setIsHeadless(false)
                .setDeviceName("Pixel6")
                .setApp(APP_FILE_ABSOLUTE_PATH)
                .setPlatformName("Android")
                .setPlatformVersion("12.0")
                .setAppPackage("com.example.appiumtest")
                .setAppActivity("com.example.appiumtest.ui.login.LoginActivity")
                .eventTimings();

        try {
            driver = new AndroidDriver(new URL("http://" + APPIUM_SERVER_URL + ":" + APPIUM_SERVER_PORT + APPIUM_SERVER_PATH), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void runAppiumServer() {
        String appiumNodeModulePath = "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js";
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumNodeModulePath))
                .withIPAddress(APPIUM_SERVER_URL)
                .withArgument(() -> "--base-path", APPIUM_SERVER_PATH));
        service.start();
    }

    public static void stopAppiumServer() {
        if (service != null) {
            service.stop();
            service = null;
        }
    }

    public static void killDriver() {
        driver.quit();
        driver = null;
    }

    private static String getAppFilePath() {
        File appDir = new File(APP_DIR);
        return new File(appDir, APP_NAME).getAbsolutePath();
    }
}