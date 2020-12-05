package com.hitesh.automatahon.helper.selenium;

import com.hitesh.automatahon.enums.LocateBy;
import com.hitesh.automatahon.thread.ThreadLocalAppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumElementHelper {
    private static final Logger logger = LogManager.getLogger(SeleniumElementHelper.class.getName());
    private static final long defaultWaitFor = 60;
    private static final int defaultWaitForJSToLoad = 30;
    private static ThreadLocal<SeleniumElementHelper> instance = new ThreadLocal<>();

    private SeleniumElementHelper() {
        // To Do
    }

    /**
     * To get instance of {@link SeleniumElementHelper}
     *
     * @return {@link SeleniumElementHelper}
     */
    public static SeleniumElementHelper getInstance() {
        if (instance.get() == null) {
            instance.set(new SeleniumElementHelper());
        }
        return instance.get();
    }

    /**
     * To wait for an Element until it's visible on Page and click
     *
     * @param element {@link WebElement} to click on
     */
    public void click(WebElement element) {
        try {

            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            SyncHelper.waitUntilElementClickable(
                    ThreadLocalAppiumDriver.getDriver(),
                    element,
                    defaultWaitFor
            );

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            //Highlight Element
            highlightElement(element);

            // Click On Element
            element.click();

            // Remove Highlight
            unhighlightElement(element);
        } catch (TimeoutException tEx) {
            logger.error(tEx.getMessage(), tEx);
        }
    }

    /**
     * To wait for an element by {@link By} Object until it's visible followed by click event
     *
     * @param by {@link By} to search & click on
     */
    public void click(By by) {
        try {

            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            SyncHelper.waitUntilElementClickable(
                    ThreadLocalAppiumDriver.getDriver(),
                    by,
                    defaultWaitFor
            );
            WebElement element = ThreadLocalAppiumDriver
                    .getDriver()
                    .findElement(by);

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight element
            highlightElement(element);

            // Click
            element.click();

            // Remove Highlight
            unhighlightElement(element);
        } catch (StaleElementReferenceException sEx) {
            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            WebElement element = ThreadLocalAppiumDriver.getDriver()
                    .findElement(by);

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight element
            highlightElement(element);

            // Click
            element.click();

            // Remove Highlight
            unhighlightElement(element);
        } catch (TimeoutException | NoSuchElementException tEx) {
            logger.error(tEx.getMessage(), tEx);
        }
    }

    /**
     * To enter a text into a {@link WebElement} but waits for it's visibility before sending keys
     *
     * @param element {@link WebElement} to where the CharSequence to send
     * @param text    Text to set in an element
     */
    public void sendKeys(WebElement element, String text) {
        try {
            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            SyncHelper.waitUntilElementVisible(
                    ThreadLocalAppiumDriver.getDriver(),
                    element,
                    defaultWaitFor
            );

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight Element
            highlightElement(element);

            element.clear();
            element.sendKeys(text);

            // Unhighlight element
            unhighlightElement(element);
        } catch (TimeoutException tEx) {
            logger.error(tEx.getMessage(), tEx);
        }
    }

    /**
     * To enter text into an element of type {@link By} but waits for it's visibility before sending keys
     *
     * @param by   {@link By} to search
     * @param text <code>String</code> to enter to an element
     */
    public void sendKeys(By by, String text) {
        try {


            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            SyncHelper.waitUntilElementVisible(
                    ThreadLocalAppiumDriver.getDriver(),
                    by,
                    defaultWaitFor
            );
            WebElement element = ThreadLocalAppiumDriver.getDriver()
                    .findElement(by);

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight Element
            highlightElement(element);

            element.clear();
            element.sendKeys(text);

            // Remove Highlight
            unhighlightElement(element);
        } catch (StaleElementReferenceException sEx) {


            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            WebElement element = ThreadLocalAppiumDriver.getDriver()
                    .findElement(by);

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight Element
            highlightElement(element);

            element.clear();
            element.sendKeys(text);

            // Unhighlight Element
            unhighlightElement(element);
        } catch (TimeoutException | NoSuchElementException tEx) {
            logger.error(tEx.getMessage(), tEx);
        }
    }

    /**
     * To get text of type {@link WebElement}
     *
     * @param element {@link WebElement} of which to get text
     * @return text inside of a particular element
     */
    public String getText(WebElement element) {


        // Wait for JS To Load
        waitForJStoLoad(defaultWaitForJSToLoad);

        String value = null;
        try {
            SyncHelper.waitUntilElementVisible(
                    ThreadLocalAppiumDriver.getDriver(),
                    element,
                    defaultWaitFor
            );

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight Element
            highlightElement(element);

            value = element.getText();

            // Remove Highlighting
            unhighlightElement(element);
        } catch (TimeoutException tEx) {
            logger.error(tEx.getMessage(), tEx);
        }
        return value;
    }

    /**
     * To get attribute value of an Element
     *
     * @param element       {@link WebElement} whose attribute value to get
     * @param attributeName Attribute name of {@link WebElement}
     * @return Value of attribute
     */
    public String getAttribute(WebElement element, String attributeName) {


        // Wait for JS To Load
        waitForJStoLoad(defaultWaitForJSToLoad);

        String value = null;
        try {
            SyncHelper.waitUntilElementVisible(
                    ThreadLocalAppiumDriver.getDriver(),
                    element,
                    defaultWaitFor
            );

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight Element
            highlightElement(element);

            value = element.getAttribute(attributeName);

            // Remove Highlighting
            unhighlightElement(element);
        } catch (TimeoutException tEx) {
            logger.error(tEx.getMessage(), tEx);
        }
        return value;
    }

    /**
     * To get attribute value of an Element
     *
     * @param by            {@link By} whose attribute value to get
     * @param attributeName Attribute name of {@link By}
     * @return Value of attribute
     */
    public String getAttribute(By by, String attributeName) {


        // Wait for JS To Load
        waitForJStoLoad(defaultWaitForJSToLoad);

        String value = null;
        try {
            SyncHelper.waitUntilElementVisible(
                    ThreadLocalAppiumDriver.getDriver(),
                    by,
                    defaultWaitFor
            );

            WebElement element = ThreadLocalAppiumDriver.getDriver()
                    .findElement(by);

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight Element
            highlightElement(element);

            value = element.getAttribute(attributeName);

            // Remove Highlighting
            unhighlightElement(element);
        } catch (StaleElementReferenceException sEx) {


            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            WebElement element = ThreadLocalAppiumDriver.getDriver()
                    .findElement(by);

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight element
            highlightElement(element);

            // Click
            element.click();

            // Remove Highlight
            unhighlightElement(element);
        } catch (TimeoutException | NoSuchElementException tEx) {
            logger.error(tEx.getMessage(), tEx);
        }
        return value;
    }

    /**
     * To get text of type {@link By}
     *
     * @param by {@link By} of which to get text
     * @return text inside of an element
     */
    public String getText(By by) {
        String value = null;
        try {


            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            SyncHelper.waitUntilElementVisible(
                    ThreadLocalAppiumDriver.getDriver(),
                    by,
                    defaultWaitFor
            );
            WebElement element = ThreadLocalAppiumDriver.getDriver()
                    .findElement(by);

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight Element
            highlightElement(element);

            value = element.getText();

            // Remove highlighting
            unhighlightElement(element);
        } catch (StaleElementReferenceException sEx) {


            // Wait for JS To Load
            waitForJStoLoad(defaultWaitForJSToLoad);

            WebElement element = ThreadLocalAppiumDriver.getDriver()
                    .findElement(by);

            // Move to Element
            SeleniumActionHelper.moveToElement(ThreadLocalAppiumDriver.getDriver(), element);

            // Highlight Element
            highlightElement(element);

            value = element.getText();

            // Remove highlighting
            unhighlightElement(element);
        } catch (TimeoutException | NoSuchElementException tEx) {
            logger.error(tEx.getMessage(), tEx);
        }
        return value;
    }

    /**
     * To check if a particular element is present in DOM or Not
     *
     * @param by {@link By} for which to check the presence of
     * @return <code>true</code> if present, <code>false</code> otherwise
     */
    public boolean isElementPresent(By by) {
        boolean isPresent = false;
        boolean isDisplayed = false;
        try {
            isPresent = ThreadLocalAppiumDriver.getDriver()
                    .findElements(by)
                    .size() > 0;

            if (isPresent) {
                // Check if it's displayed
                isDisplayed = ThreadLocalAppiumDriver.getDriver()
                        .findElements(by)
                        .stream()
                        .allMatch(WebElement::isDisplayed);

            }
        } catch (Exception ignored) {
        }
        return isPresent && isDisplayed;
    }

    /**
     * To highlight an element with Red Border
     *
     * @param element {@link WebElement} to highlight
     */
    public void highlightElement(WebElement element) {
        try {
            getJSExecutor()
                    .executeScript("arguments[0].style.border='3px inset red'", element);
        } catch (Exception ignored) {
        }

    }

    /**
     * To highlight an element with specific border color
     *
     * @param element              {@link WebElement} to highlight
     * @param colorToHighlightWith Highlight with color
     */
    public void highlightElement(WebElement element, String colorToHighlightWith) {
        try {
            getJSExecutor()
                    .executeScript("arguments[0].style.border='3px inset " + colorToHighlightWith + "'", element);
        } catch (Exception ignored) {
        }
    }

    /**
     * To unhighlight and element after highlighting it
     *
     * @param element {@link WebElement} to unhighlight
     */
    public void unhighlightElement(WebElement element) {
        try {
            getJSExecutor()
                    .executeScript("arguments[0].style.border=''", element);
        } catch (Exception ignored) {
        }
    }

    /**
     * To check whether an element is displayed on DOM or not
     *
     * @param by {@link By} of element
     * @return <code>true</code> if displayed, <code>false</code> otherwise
     */
    public boolean isElementDisplayed(By by) {
        boolean isDisplayed = false;
        try {
            // check if element is present
            if (isElementPresent(by)) {
                isDisplayed = ThreadLocalAppiumDriver.getDriver()
                        .findElement(by)
                        .isDisplayed();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return isDisplayed;
    }

    /**
     * To check if a WebElement is displayed on DOM or not
     *
     * @param element {@link WebElement} to check
     * @return <code>true</code> if displayed, <code>false</code> otherwise
     */
    public boolean isElementDisplayed(WebElement element) {
        boolean isDisplayed = false;
        try {
            isDisplayed = element.isDisplayed();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return isDisplayed;
    }

    /**
     * To wait until JQuery & JavaScripts are loaded
     *
     * @param seconds Timeout in seconds to wait for JQuery & JavaScript to load
     * @return <code>true</code> if loaded in timeout specified, <code>false</code> otherwise
     */
    public boolean waitForJStoLoad(int seconds) {
        try {
            final JavascriptExecutor jse = getJSExecutor();
            WebDriverWait wait = getWaitObject(seconds);

            // wait for jQuery to load
            ExpectedCondition<Boolean> jQueryLoad = driver -> {
                try {
                    return ((Long) jse.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            };

            // wait for Javascript to load
            ExpectedCondition<Boolean> jsLoad = driver -> jse.executeScript("return document.readyState")
                    .toString().equals("complete");

            return wait.until(jQueryLoad) && wait.until(jsLoad);
        } catch (Exception ex) {
            logger.info("JS did not load in " + seconds + " seconds");
        }
        return false;
    }

    /**
     * To delete all the cookies before starting browser
     */
    public void deleteAllCookies() {
        ThreadLocalAppiumDriver.getDriver()
                .manage()
                .deleteAllCookies();
    }

    /**
     * To delete a specific cookie of type {@link Cookie}
     *
     * @param cookie {@link Cookie} to delete
     */
    public void deleteCookie(Cookie cookie) {
        try {
            ThreadLocalAppiumDriver.getDriver()
                    .manage()
                    .deleteCookie(cookie);
        } catch (Exception ex) {
            logger.error("Error deleting cookie.");
        }

    }

    /**
     * To delete a specific cookie with it's name.
     *
     * @param cookieName Cookie to delete having specified name
     */
    public void deleteCookieNamed(String cookieName) {
        try {
            ThreadLocalAppiumDriver.getDriver()
                    .manage()
                    .deleteCookieNamed(cookieName);
        } catch (Exception ex) {
            logger.error("Error deleting cookied named : " + cookieName);
        }

    }

    /**
     * To execute a JavaScript
     *
     * @param jsScript JavaScript to execute
     */
    public Object executeScript(String jsScript) {
        return getJSExecutor()
                .executeScript(jsScript);
    }

    /**
     * To enter text into a specific textbox using JavaScript
     *
     * @param element {@link WebElement} at where to set text
     * @param text    Text to set in an element
     */
    public void sendKeysWithJs(WebElement element, String text) {
        try {
            getJSExecutor()
                    .executeScript("arguments[0].setAttribute('value', '" + text + "');", element);

            logger.info("Send [" + text + "] to an element using JavaScript.");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * To get current url of a browser session
     *
     * @return Current Website url
     */
    public String getCurrentUrl() {
        return ThreadLocalAppiumDriver.getDriver()
                .getCurrentUrl();
    }

    /**
     * To get text value of an element using JavaScript
     *
     * @param by      {@link LocateBy} with which to locate element
     * @param locator Locator value
     * @return Text value, null otherwise
     */
    public String getTextByJS(LocateBy by, String locator) {
        String textValue = null;

        switch (by) {
            case ID:
                textValue = getJSExecutor()
                        .executeScript("return document.getElementById('" + locator + "').value")
                        .toString();
                break;
            case NAME:
                textValue = getJSExecutor()
                        .executeScript("return document.getElementsByName('" + locator + "')[0].value")
                        .toString();
                break;
            case TAG_NAME:
                textValue = getJSExecutor()
                        .executeScript("return document.getElementsByTagName('" + locator + "')[0].value")
                        .toString();
                break;
            case CLASS_NAME:
                textValue = getJSExecutor()
                        .executeScript("return document.getElementsByClassName('" + locator + "')[0].value")
                        .toString();
                break;
        }


        return textValue;
    }

    /**
     * To click on an Element using JS
     *
     * @param by      Locator Strategy
     * @param locator Locator Value
     */
    public void clickByJS(LocateBy by, String locator) {

        switch (by) {
            case ID:
                getJSExecutor()
                        .executeScript("document.getElementById('" + locator + "').click();");
                break;
            case NAME:
                getJSExecutor()
                        .executeScript("document.getElementsByName('" + locator + "')[0].click();");
                break;
            case TAG_NAME:
                getJSExecutor()
                        .executeScript("document.getElementsByTagName('" + locator + "')[0].click();");
                break;
            case CLASS_NAME:
                getJSExecutor()
                        .executeScript("document.getElementsByClassName('" + locator + "')[0].click();");
                break;
        }
    }

    /**
     * To get a Shadow Root Element Using JavaScript
     *
     * @param element Parent Element under which to find the Shadow Element
     * @return Shadow {@link WebElement}
     */
    public WebElement expandRootElement(WebElement element) {
        WebElement shadowRootElement = (WebElement) getJSExecutor()
                .executeScript("return arguments[0].shadowRoot", element);
        return shadowRootElement;
    }

    /**
     * To find an element using {@link By} method
     *
     * @param by {@link By} locator
     * @return {@link WebElement}
     */
    public WebElement findElement(By by) {
        return getWaitObject(30)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * To get the instance of {@link JavascriptExecutor}
     *
     * @return {@link JavascriptExecutor} object
     */
    public JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) ThreadLocalAppiumDriver.getDriver();
    }

    /**
     * To get Object of {@link WebDriverWait}
     *
     * @param timeOutInSeconds Timeout in seconds to wait for
     * @return {@link WebDriverWait}
     */
    public WebDriverWait getWaitObject(long timeOutInSeconds) {
        return new WebDriverWait(ThreadLocalAppiumDriver.getDriver(), timeOutInSeconds);
    }


}
