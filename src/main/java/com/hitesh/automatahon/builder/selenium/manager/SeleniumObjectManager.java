package com.hitesh.automatahon.builder.selenium.manager;

import com.hitesh.automatahon.thread.ThreadLocalSEDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumObjectManager {

    private static final Logger logger = LogManager.getLogger(SeleniumObjectManager.class.getName());
    private static ThreadLocal<SeleniumObjectManager> instance = new ThreadLocal<>();

    private SeleniumObjectManager() {
        // To Do
    }

    /**
     * To get thread safe instance of Object Helper
     *
     * @return <code>ObjectHelper</code>
     */
    public static SeleniumObjectManager getInstance() {
        if (instance.get() == null) {
            instance.set(new SeleniumObjectManager());
        }
        return instance.get();
    }

    /**
     * To get thread safe instance of {@link WebDriver}
     *
     * @return {@link WebDriver}
     */
    public WebDriver getDriver() {
        if (ThreadLocalSEDriver.getDriver() != null) {
            return ThreadLocalSEDriver.getDriver();
        } else {
            throw new NullPointerException("Driver session is not created. Please initialize Driver Object Before calling getDriver() function.");
        }
    }

    /**
     * To check whether the driver session is created or not
     *
     * @return true if driver session created, false otherwise
     */
    public boolean isDriverSessionCreated() {
        if (ThreadLocalSEDriver.getDriver() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * To get Object of {@link WebDriverWait}
     *
     * @param timeOutInSeconds Timeout in seconds to wait for
     * @return {@link WebDriverWait}
     */
    public WebDriverWait getWaitObject(long timeOutInSeconds) {
        return new WebDriverWait(ThreadLocalSEDriver.getDriver(), timeOutInSeconds);
    }

    /**
     * To get Object of {@link FluentWait}.
     *
     * <p> Exception to be ignored. </p>
     * <p> 1. {@link StaleElementReferenceException} </p>
     * <p> 2. {@link TimeoutException} </p>
     * <p> 3. {@link NoSuchElementException} </p>
     *
     * @param inSeconds       Timeout in seconds to wait for
     * @param pollEveryMillis Poll in every N milliseconds
     * @return {@link FluentWait}
     */
    public FluentWait<WebDriver> getFluentWait(long inSeconds, long pollEveryMillis) {
        return new FluentWait<>(ThreadLocalSEDriver.getDriver())
                .withTimeout(Duration.ofSeconds(inSeconds))
                .pollingEvery(Duration.ofMillis(pollEveryMillis))
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    /**
     * To get the instance of {@link JavascriptExecutor}
     *
     * @return {@link JavascriptExecutor} object
     */
    public JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) ThreadLocalSEDriver.getDriver();
    }
}
