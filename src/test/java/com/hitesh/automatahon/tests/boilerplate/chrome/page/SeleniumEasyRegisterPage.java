package com.hitesh.automatahon.tests.boilerplate.chrome.page;

import com.hitesh.automatahon.helper.selenium.SeleniumElementHelper;
import org.openqa.selenium.By;

public class SeleniumEasyRegisterPage {
    By firstNameInputBy = By.cssSelector("[name='first_name']");
    By lastNameInputBy = By.cssSelector("[name='last_name']");
    By emailInputBy = By.cssSelector("[name='email']");
    By phoneInputBy = By.cssSelector("[name='phone']");
    By addressInputBy = By.cssSelector("[name='address']");
    By cityInputBy = By.cssSelector("[name='city']");
    By zipCodeInputBy = By.cssSelector("[name='zip']");
    By websiteInputBy = By.cssSelector("[name='website']");
    By hasHostingYesRadioBy = By.xpath("//input[@name='hosting'][@value='yes']");
    By hasHostingNoRadioBy = By.xpath("//input[@name='hosting'][@value='no']");
    By projectDescriptionTextAreaBy = By.cssSelector("[name='comment']");

    public SeleniumEasyRegisterPage() {
        // To Do
    }

    public SeleniumEasyRegisterPage firstName(String firstName) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(firstNameInputBy, firstName);
        return this;
    }

    public SeleniumEasyRegisterPage lastName(String lastName) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(lastNameInputBy, lastName);
        return this;
    }

    public SeleniumEasyRegisterPage email(String email) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(emailInputBy, email);
        return this;
    }

    public SeleniumEasyRegisterPage phone(String phone) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(phoneInputBy, phone);
        return this;
    }

    public SeleniumEasyRegisterPage address(String address) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(addressInputBy, address);
        return this;
    }

    public SeleniumEasyRegisterPage city(String city) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(cityInputBy, city);
        return this;
    }

    public SeleniumEasyRegisterPage zipCode(String postalcode) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(zipCodeInputBy, postalcode);
        return this;
    }

    public SeleniumEasyRegisterPage website(String website) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(websiteInputBy, website);
        return this;
    }

    public SeleniumEasyRegisterPage hasHosting(boolean hasHosting) {
        if (hasHosting) {
            SeleniumElementHelper
                    .getInstance()
                    .click(hasHostingYesRadioBy);
        } else {
            SeleniumElementHelper
                    .getInstance()
                    .click(hasHostingNoRadioBy);
        }
        return this;
    }

    public SeleniumEasyRegisterPage projectDescription(String projectDescription) {
        SeleniumElementHelper
                .getInstance()
                .sendKeys(projectDescriptionTextAreaBy, projectDescription);
        return this;
    }
}
