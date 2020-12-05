package com.hitesh.automatahon.helper.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumActionHelper {
    private static final Logger logger = LogManager.getLogger(SeleniumActionHelper.class.getName());

    private SeleniumActionHelper() {
        // To Do
    }

    public static void moveToElement(WebDriver driver, WebElement elementToMove) {
        new Actions(driver)
                .moveToElement(elementToMove)
                .build()
                .perform();
    }

    public void moveToElement(WebDriver driver, By by) {
        new Actions(driver)
                .moveToElement(driver.findElement(by))
                .build()
                .perform();
    }

}
