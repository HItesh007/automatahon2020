package com.hitesh.automatahon.helper.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SyncHelper {
    /**
     * To wait until element is visible.
     *
     * @param driver           Object of {@link WebDriver}
     * @param element          {@link WebElement} to wait for
     * @param timeOutInSeconds Timeout in seconds to wait for
     */
    public static void waitUntilElementVisible(WebDriver driver, WebElement element, long timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * To wait until the element is visible by {@link By}
     *
     * @param driver           Object of {@link WebDriver}
     * @param by               {@link By} to wait for
     * @param timeOutInSeconds Timeout in seconds to wait for
     */
    public static void waitUntilElementVisible(AppiumDriver<MobileElement> driver, By by, long timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * To wait until element is present in DOM.
     *
     * @param driver           Object of {@link WebDriver}
     * @param by               {@link By} to wait for
     * @param timeOutInSeconds Timeout in seconds to wait for
     */
    public static void waitUntilElementPresence(WebDriver driver, By by, long timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * To wait until a specific text is visible in an {@link WebElement}
     *
     * @param driver           Object of {@link WebDriver}
     * @param element          {@link WebElement} at which to wait for a text
     * @param textToWaitFor    Text to wait for in a element
     * @param timeOutInSeconds Timeout in seconds to wait for
     */
    public static void waitUntilTextToBePresentInElement(WebDriver driver, WebElement element, String textToWaitFor, long timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.textToBePresentInElement(element, textToWaitFor));
    }

    /**
     * To wait until Element is clickable
     *
     * @param driver           Object of {@link WebDriver}
     * @param element          {@link WebElement} to wait for
     * @param timeOutInSeconds Timeout in seconds to wait for
     */
    public static void waitUntilElementClickable(WebDriver driver, WebElement element, long timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * To wait until Element is clickable by {@link By}
     *
     * @param driver           Object of {@link WebDriver}
     * @param by               {@link By} to wait for
     * @param timeOutInSeconds Timeout in seconds to wait for
     */
    public static void waitUntilElementClickable(WebDriver driver, By by, long timeOutInSeconds) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

}
