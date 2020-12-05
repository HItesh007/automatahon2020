package com.hitesh.automatahon.builder.selenium.driver;

import com.hitesh.automatahon.builder.selenium.enums.Browser;
import com.hitesh.automatahon.thread.ThreadLocalSEDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class.getName());
    private static ThreadLocal<DriverFactory> instance = new ThreadLocal<>();
    private FirefoxOptions firefoxOptions;
    private ChromeOptions chromeOptions;

    public DriverFactory() {
        // To Do
    }

    /**
     * To get static instance of Driver Factory Class
     *
     * @return <code>DriverFactory</code>
     */
    public static DriverFactory getInstance() {
        if (instance.get() == null) {
            instance.set(new DriverFactory());
        }
        return instance.get();
    }

    /**
     * To set the Object of <code>ChromeOptions</code> before initializing
     * browser instance
     *
     * @param optionsAs Object of type <code>ChromeOptions</code>
     * @return <code>DriverFactory</code>
     */
    public DriverFactory withBrowserOptionsAs(ChromeOptions optionsAs) {
        this.chromeOptions = new ChromeOptions();
        chromeOptions = optionsAs;
        return this;
    }

    /**
     * To set the Object of <code>FirefoxOptions</code> before initializing
     * browser instance
     *
     * @param firefoxOptionsAs Object of type <code>FirefoxOptions</code>
     * @return <code>DriverFactory</code>
     */
    public DriverFactory withBrowserOptionsAs(FirefoxOptions firefoxOptionsAs) {
        this.firefoxOptions = new FirefoxOptions();
        firefoxOptions = firefoxOptionsAs;
        return this;
    }

    /**
     * To set Implicit wait for <code>WebDriver</code> instance.
     *
     * @param time     Time value in <code>long</code>
     * @param timeUnit <code>TimeUnit</code>
     * @return <code>DriverFactory</code>
     */
    public DriverFactory setImplicitTimeoutAs(long time, TimeUnit timeUnit) {
        if (ThreadLocalSEDriver.getDriver() != null) {
            ThreadLocalSEDriver.getDriver().manage().timeouts().implicitlyWait(time, timeUnit);
        } else {
            throw new NullPointerException("WebDriver instance is not created. Please call initBrowser() before setImplicitTimeout method");
        }
        return this;
    }

    /**
     * To set Asynchronous script to time out after specified time
     *
     * @param time     Timeout value in <code>long</code>
     * @param timeUnit <code>TimeUnit</code> in Seconds, Milliseconds, minutes, hours etc.
     * @return <code>DriverFactory</code>
     */
    public DriverFactory setScriptTimeoutAs(long time, TimeUnit timeUnit) {
        if (ThreadLocalSEDriver.getDriver() != null) {
            ThreadLocalSEDriver.getDriver().manage().timeouts().setScriptTimeout(time, timeUnit);
        } else {
            throw new NullPointerException("WebDriver instance is not created. Please call initBrowser() before setScriptTimeout method");
        }
        return this;
    }

    /**
     * To set page load timeout
     *
     * @param time     Timeout value in <code>long</code>
     * @param timeUnit <code>TimeUnit</code> in Seconds, Milliseconds, minutes, hours etc.
     * @return <code>DriverFactory</code>
     */
    public DriverFactory setPageLoadTimeoutAs(long time, TimeUnit timeUnit) {
        if (ThreadLocalSEDriver.getDriver() != null) {
            ThreadLocalSEDriver.getDriver().manage().timeouts().pageLoadTimeout(time, timeUnit);
        } else {
            throw new NullPointerException("WebDriver instance is not created. Please call initBrowser() before setPageLoadTimeout method");
        }
        return this;
    }

    /**
     * To download binary for the browser specified as parameter
     * and create instance of it
     *
     * @param browser Browser to initialize of type <code>Browser</code>
     * @return <code>DriverFactory</code>
     */
    public DriverFactory initBrowser(Browser browser) {
        switch (browser) {
            case IE:
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ThreadLocalSEDriver
                        .setDriver(new InternetExplorerDriver(ieOptions));
                break;
            case Edge:
                WebDriverManager.edgedriver().setup();
                break;
            case Firefox:
                WebDriverManager.firefoxdriver().setup();
                if (firefoxOptions != null) {
                    ThreadLocalSEDriver
                            .setDriver(new FirefoxDriver(firefoxOptions));
                } else {
                    ThreadLocalSEDriver
                            .setDriver(new FirefoxDriver());
                }

                break;
            default:
                WebDriverManager.chromedriver().setup();
                if (chromeOptions != null) {
                    ThreadLocalSEDriver
                            .setDriver(new ChromeDriver(chromeOptions));
                } else {
                    ThreadLocalSEDriver
                            .setDriver(new ChromeDriver());
                }

                break;
        }

        // Maximize browser
        ThreadLocalSEDriver.getDriver().manage().window().maximize();
        return this;
    }


    /**
     * To set the Web Url.
     *
     * @param webUrl Web Url of Application
     */
    public void setUrl(String webUrl) {
        ThreadLocalSEDriver.getDriver().get(webUrl);
    }

    /**
     * To close the browser and clean the driver service.
     */
    public void closeBrowser() {
        try {
            if (ThreadLocalSEDriver.getDriver() != null) {
                ThreadLocalSEDriver
                        .getDriver()
                        .close();

                if (ThreadLocalSEDriver.getDriver() instanceof ChromeDriver) {
                    ThreadLocalSEDriver
                            .getDriver()
                            .quit();
                }
            }
        } catch (WebDriverException ex) {
            logger.error(ex.getMessage(), ex);
        }

        // set value to null
        ThreadLocalSEDriver
                .setDriver(null);
    }
}
