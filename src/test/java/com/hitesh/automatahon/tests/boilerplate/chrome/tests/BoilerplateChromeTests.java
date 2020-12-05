package com.hitesh.automatahon.tests.boilerplate.chrome.tests;

import com.github.javafaker.Faker;
import com.hitesh.automatahon.helper.appium.AppiumSyncHelper;
import com.hitesh.automatahon.tests.boilerplate.chrome.page.SeleniumEasyRegisterPage;
import com.hitesh.automatahon.thread.ThreadLocalAppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class BoilerplateChromeTests {
    private static final Logger logger = LogManager.getLogger(BoilerplateChromeTests.class.getName());

    @Test(priority = 0)
    public void seleniumEasyTest() {
        ThreadLocalAppiumDriver.getDriver().get("https://www.seleniumeasy.com/test/input-form-demo.html");
        logger.info("URL : " + ThreadLocalAppiumDriver.getDriver().getTitle());

        Faker faker = new Faker();

        new SeleniumEasyRegisterPage()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().safeEmailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .address(faker.address().fullAddress())
                .city(faker.address().city())
                .zipCode(faker.address().zipCode())
                .website(faker.internet().domainName())
                .hasHosting(faker.random().nextBoolean())
                .projectDescription(faker.lorem().paragraph());


        logger.info("Waiting for 10 second before exiting @Test block");
        new AppiumSyncHelper().pauseScript(10);
    }
}
