package com.hitesh.automatahon.thread;

import org.openqa.selenium.WebDriver;

public class ThreadLocalSEDriver {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * To get Thread Safe object of WebDriver
     *
     * @return <code>WebDriver</code> <b>Thread safe object of WebDriver</b>
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * To set Thread Safe object of WebDriver
     *
     * @param driverInstance <code>WebDriver</code> <b>Object of WebDriver instance</b>
     */
    public static void setDriver(WebDriver driverInstance) {
        tlDriver.set(driverInstance);
    }

}
