package com.hitesh.automatahon.builder.appium.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

class IOSCapabilities {
    DesiredCapabilities iOSCapabilities;

    public IOSCapabilities(DesiredCapabilities generalCapabilities) {
        this.iOSCapabilities = generalCapabilities;
    }
}
