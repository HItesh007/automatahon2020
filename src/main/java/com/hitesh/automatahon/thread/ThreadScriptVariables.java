package com.hitesh.automatahon.thread;

import org.openqa.selenium.remote.DesiredCapabilities;

public class ThreadScriptVariables {
    private static String serverURI;
    private static ThreadLocal<DesiredCapabilities> sessionCapabilityMap = new ThreadLocal<>();

    public static String getServerURI() {
        return serverURI;
    }

    public static void setServerURI(String serverUrl) {
        serverURI = serverUrl;
    }

    public static DesiredCapabilities getSessionCapability() {
        return sessionCapabilityMap.get();
    }

    public static void setSessionCapability(DesiredCapabilities sessionCapability) {
        sessionCapabilityMap.set(sessionCapability);
    }
}
