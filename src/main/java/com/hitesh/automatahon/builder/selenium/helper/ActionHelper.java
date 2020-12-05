package com.hitesh.automatahon.builder.selenium.helper;

import com.hitesh.automatahon.thread.ThreadLocalSEDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionHelper {
    private static final Logger logger = LogManager.getLogger(ActionHelper.class.getName());
    private static ThreadLocal<ActionHelper> instance = new ThreadLocal<>();

    private ActionHelper() {
        // To Do
    }

    public static ActionHelper getInstance() {
        if (instance.get() == null) {
            instance.set(new ActionHelper());
        }
        return instance.get();
    }

    public void moveToElement(WebElement elementToMove) {
        new Actions(ThreadLocalSEDriver.getDriver())
                .moveToElement(elementToMove)
                .build()
                .perform();
    }

    public void moveToElement(By by) {
        new Actions(ThreadLocalSEDriver.getDriver())
                .moveToElement(ThreadLocalSEDriver.getDriver().findElement(by))
                .build()
                .perform();
    }
}
