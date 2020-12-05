package com.hitesh.automatahon.builder.appium.session;

import com.hitesh.automatahon.builder.appium.capabilities.DesiredCapabilityBuilder;
import com.hitesh.automatahon.helper.adb.ADBHelper;
import com.hitesh.automatahon.helper.appium.AppiumSyncHelper;
import com.hitesh.automatahon.thread.ThreadAppiumServer;
import com.hitesh.automatahon.thread.ThreadLocalAppiumDriver;
import com.hitesh.automatahon.thread.ThreadScriptVariables;
import com.hitesh.automatahon.utils.PropertiesUtility;
import com.hitesh.automatahon.utils.SystemUtility;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumSessionBuilder {
    private static final String USER_DIR = SystemUtility.getUserDirectory();
    private static final String FILE_SEPARATOR = SystemUtility.getFileSeparator();
    private static final Logger logger = LogManager.getLogger(AppiumSessionBuilder.class.getName());
    private static String APPIUM_SERVER_URL = null;

    @BeforeSuite(alwaysRun = true)
    @Parameters({"appium-server-ip", "chrome-auto-download"})
    public void startAppiumServer(String appiumServerIPAddress, @Optional String chromeAutoDownload) {

        String normalizedServerIPAddress = StringUtils.normalizeSpace(appiumServerIPAddress);
        boolean shouldAutoDownloadChrome = false;

        if (!StringUtils.isBlank(chromeAutoDownload)) {
            shouldAutoDownloadChrome = Boolean.parseBoolean(chromeAutoDownload);
        }

        /*// Build Appium Service
        ThreadAppiumServer
                .setAppiumServerDriverService
                        (
                                new AppiumServerBuilder()
                                        .buildAppiumServerProcess(normalizedServerIPAddress, shouldAutoDownloadChrome)
                        );

        // Start Appium Service
        ThreadAppiumServer
                .getAppiumServerDriverService()
                .start();

        // Set appium server
        APPIUM_SERVER_URL = ThreadAppiumServer.getAppiumServerDriverService().getUrl().toString();*/

        String configFilePath = USER_DIR + FILE_SEPARATOR +
                "configs" + FILE_SEPARATOR +
                "config.properties";

        // Load Properties file and get Appium server host and port details
        PropertiesUtility propUtility = new PropertiesUtility();

        //set property file path
        propUtility.setPropertyFilePath(configFilePath);

        // load property file for read
        propUtility.loadPropertyFileForReading();

        // Get Appium Serve Host
        String APPIUM_SERVER_HOST = propUtility.getProperty("APPIUM_HOST");

        // Get Appium Server Port
        String APPIUM_SERVER_PORT = propUtility.getProperty("APPIUM_PORT");

        // Get Appium Server Protocol
        String APPIUM_SERVER_PROTOCOL = propUtility.getProperty("APPIUM_PROTOCOL");

        // new URL("https://127.0.0.1:4723/wd/hub")
        APPIUM_SERVER_URL = APPIUM_SERVER_PROTOCOL
                .concat("://")
                .concat(APPIUM_SERVER_HOST)
                .concat(":")
                .concat(APPIUM_SERVER_PORT)
                .concat("/wd/hub");

        String logDetails = "Appium Server Started At : " + APPIUM_SERVER_URL;
        ThreadScriptVariables.setServerURI(APPIUM_SERVER_URL);
        logger.info(logDetails);
    }

    @BeforeTest(alwaysRun = true)
    @Parameters({"device-name", "platform-name", "platform-version", "udid", "automation-name",
            "wda-system-port", "browser-name", "app-package", "app-activity", "bundle-id"})
    public void initDevice(String deviceName, String platformName, String platformVersion,
                           String udid, String automationName, String webDriverPort,
                           @Optional String browserName,
                           @Optional String androidAppPackage,
                           @Optional String androidAppActivity,
                           @Optional String iOSBundleId) {
        try {

            String normalizedPlatformName = StringUtils.normalizeSpace(platformName).toUpperCase();

            // Set Thread Context with Device Name
            ThreadContext.put("DEVICE_NAME", StringUtils.normalizeSpace(deviceName));

            // Wait for 5 second before start
            new AppiumSyncHelper().pauseScript(5);

            if (normalizedPlatformName.equalsIgnoreCase(Platform.ANDROID.toString())) {

                // Setup Android Device
                androidDeviceSetup(
                        deviceName,
                        platformName,
                        platformVersion,
                        udid,
                        automationName,
                        webDriverPort,
                        browserName,
                        androidAppPackage,
                        androidAppActivity
                );
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private void androidDeviceSetup(String aDeviceName, String aPlatformName, String aPlatformVersion,
                                    String aUDID, String aAutomationName, String aWdaSysPort,
                                    String aBrowserName, String aAppPackage, String aAppActivity) {
        try {

            // Unlock Device If Locked
            new ADBHelper().unlockDevice(aUDID);

            String logDetails = "Device [" + aDeviceName + "] has Appium Server URL As : " + APPIUM_SERVER_URL;
            logger.info(logDetails);

            DesiredCapabilityBuilder desiredCapabilityBuilder = new DesiredCapabilityBuilder();

            // Start Building Capabilities
            desiredCapabilityBuilder
                    .deviceName(aDeviceName)
                    .platformName(aPlatformName)
                    .platformVersion(aPlatformVersion)
                    .udid(aUDID)
                    .automationName(aAutomationName)
                    .setNewCommandTimeoutAs(300000);

            DesiredCapabilities aCaps;

            if (!StringUtils.isBlank(aBrowserName)) {
                String chromeDriverExeDirPath = USER_DIR +
                        FILE_SEPARATOR +
                        "chrome-drivers" +
                        FILE_SEPARATOR +
                        "drivers";

                String chromeDriverMappingFilePath = USER_DIR +
                        FILE_SEPARATOR +
                        "chrome-drivers" +
                        FILE_SEPARATOR +
                        "chrome-mapping";

                File chromeDriverExeFile = new File(chromeDriverExeDirPath);
                File chromeDriverMappingFile = new File(chromeDriverMappingFilePath);

                if (!chromeDriverExeFile.exists()) {
                    chromeDriverExeFile.mkdirs();
                }

                if (!chromeDriverMappingFile.exists()) {
                    chromeDriverMappingFile.mkdirs();
                }

                aCaps = desiredCapabilityBuilder
                        .browserAs(aBrowserName)
                        .android()
                        .hideKeyboard()
                        .wdaPort(Integer.parseInt(aWdaSysPort))
                        .uiAutomator2ServerLaunchTimeout(300000)
                        .chromeDriverExecutableDirectory(chromeDriverExeDirPath)
                        .chromeDriverVersionMappingFile(chromeDriverMappingFilePath + FILE_SEPARATOR + "chrome-driver-mappings.json")
                        .build();
            } else {
                aCaps = desiredCapabilityBuilder
                        .android()
                        .wdaPort(Integer.parseInt(aWdaSysPort))
                        .hideKeyboard()
                        .setAppPackageAs(aAppPackage)
                        .setAppActivityAs(aAppActivity)
                        .uiAutomator2ServerLaunchTimeout(300000)
                        .build();
            }

            // Start Android Driver instance
            ThreadLocalAppiumDriver.setDriver(new AndroidDriver<MobileElement>(new URL(APPIUM_SERVER_URL), aCaps));
            ThreadLocalAppiumDriver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (!StringUtils.isBlank(aBrowserName)) {
                ThreadLocalAppiumDriver.getDriver().manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);
            }

            // Set Session Capability
            ThreadScriptVariables.setSessionCapability(aCaps);
        } catch (Exception ex) {
            // add exception to list
            logger.error(ex.getMessage(), ex);
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDownDevice() {
        try {
            // Hard wait for 5 second before closing the app
            logger.info("Waiting for 5 seconds before closing app and quitting driver.");
            Thread.sleep(5000);

            if (ThreadLocalAppiumDriver.getDriver() != null) {

                ThreadLocalAppiumDriver.getDriver().getCapabilities();
                // Close App Under Test
                logger.info("Closing app under test...!!!");

                ThreadLocalAppiumDriver.getDriver().closeApp();

                // Quit Appium Driver session
                logger.info("Deleting Appium Driver Session...!!!");

                ThreadLocalAppiumDriver.getDriver().quit();

            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void stopAppiumServer() {
        try {
            Thread.sleep(5000);

            if (ThreadAppiumServer.getAppiumServerDriverService().isRunning()) {
                // Close appium server
                ThreadAppiumServer.getAppiumServerDriverService().stop();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
