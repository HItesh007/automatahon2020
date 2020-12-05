package com.hitesh.automatahon.helper.testng;

import com.hitesh.automatahon.enums.DeviceProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.*;

public class TestNGHelper {

    private static final Logger logger = LogManager.getLogger(TestNGHelper.class.getName());
    private String suiteName;
    private boolean isParallel = false;
    private List<String> listOfPackages = new ArrayList<>();
    private ParallelMode _parallelMode;
    private String automationName = "UiAutomator2";
    private int portNumberStartWDA = 8201;
    private String appUnderTestPackageName;
    private String appUnderTestActivityName;


    public TestNGHelper() {
        // To Do
    }

    /**
     * To set the name of Suite
     *
     * @param suiteNameAs <code>String</code> Suite Name to set
     * @return <code>TestNGHelper</code>
     */
    public TestNGHelper setSuiteNameAs(String suiteNameAs) {
        this.suiteName = suiteNameAs;
        return this;
    }

    /**
     * To set list of classes where Test has been created
     *
     * @param classes <code>List{@literal <}String{@literal >}</code> List of fully qualified class name where Tests are located
     * @return <code>TestNGHelper</code>
     */
    public TestNGHelper havingTestClassesAs(List<String> classes) {
        this.listOfPackages = classes;
        return this;
    }

    /**
     * To set the value of Parallel execution
     *
     * @param _isParallel <code>boolean</code> True to run in parallel, false otherwise
     * @return <code>TestNGHelper</code>
     */
    public TestNGHelper setParallel(boolean _isParallel) {
        this.isParallel = _isParallel;
        return this;
    }

    /**
     * To set the mode of Parallel Execution
     *
     * @param parallelMode <code>ParallelMode</code> Mode of parallel execution
     * @return <code>TestNGHelper</code>
     */
    public TestNGHelper parallelMode(ParallelMode parallelMode) {
        this._parallelMode = parallelMode;
        return this;
    }

    /**
     * To set the name of Automation for running the test script.
     * i.e. Appium, UiAutomator1, UiAutomator2 etc
     *
     * @param automationNameAs <code>String</code> Automation name capability to be set for all tests
     * @return <code>TestNGHelper</code>
     */
    public TestNGHelper setAutomationNameAs(String automationNameAs) {
        this.automationName = automationNameAs;
        return this;
    }

    /**
     * To set the start port number to be used when building testng file
     *
     * @param startFromPort <code>int</code> System or WDA port number for each devices to set.
     * @return <code>TestNGHelper</code>
     */
    public TestNGHelper wdaPortStartFrom(int startFromPort) {
        this.portNumberStartWDA = startFromPort;
        return this;
    }

    /**
     * To set the package name of App Under Test
     *
     * @param aPackageName <code>String</code> Package name of App Under Test
     * @return <code>TestNGHelper</code>
     */
    public TestNGHelper setAppUnderTestPackage(String aPackageName) {
        this.appUnderTestPackageName = aPackageName;
        return this;
    }

    /**
     * To set the activity name of App Under Test
     *
     * @param aActivityName <code>String</code> Name of App Activity of App Under Test
     * @return <code>TestNGHelper</code>
     */
    public TestNGHelper setAppUnderTestActivity(String aActivityName) {
        this.appUnderTestActivityName = aActivityName;
        return this;
    }

    /**
     * To generate xml file for all the devices connected
     *
     * @param listOfDevices <code>LinkedHashMap</code> List Of Devices
     * @return <code>TestNGHelper</code>
     */
    public List<XmlSuite> generateTestNGXml(LinkedHashMap<String, Map<String, String>> listOfDevices) {
        try {

            // get total number of devices
            int totalDevices = listOfDevices.size();

            // Create a list to XmlSuites
            List<XmlSuite> listOfSuites = new ArrayList<>();

            if (totalDevices > 0) {
                // Create object of XmlSuite and provide name to that suite
                XmlSuite xmlSuite = getXmlSuite(suiteName);

                // Set run configuration in xml file
                if (isParallel) {
                    switch (_parallelMode) {
                        case CLASSES:
                            setSuiteRunConfiguration(xmlSuite, XmlSuite.ParallelMode.CLASSES);
                            break;
                        case TESTS:
                            setSuiteRunConfiguration(xmlSuite, XmlSuite.ParallelMode.TESTS);
                            break;
                        case METHODS:
                            setSuiteRunConfiguration(xmlSuite, XmlSuite.ParallelMode.METHODS);
                            break;
                        case INSTANCES:
                            setSuiteRunConfiguration(xmlSuite, XmlSuite.ParallelMode.INSTANCES);
                            break;
                        default:
                            setSuiteRunConfiguration(xmlSuite, XmlSuite.ParallelMode.NONE);
                            break;
                    }

                    // set thread count to total number of devices
                    xmlSuite.setThreadCount(totalDevices);
                }

                //Create a list of XmlTests to add the Xmltest that will be created from below script
                List<XmlTest> suiteTests = new ArrayList<>();

                // set parameters for test including PrimaryKey of Device
                listOfDevices.forEach((deviceId, deviceMap) -> {

                    String deviceUDID = deviceMap.get(DeviceProperty.DEVICE_ID.toString());
                    String deviceModel = deviceMap.get(DeviceProperty.DEVICE_MODEL.toString());
                    String platformVersion = deviceMap.get(DeviceProperty.PLATFORM_VERSION.toString());
                    String testName = deviceModel + " (" + deviceUDID + ") - Follow Test";

                    //Create an instance of XmlTest and assign a name for it.
                    XmlTest deviceTest = getXmlTest(xmlSuite, testName);

                    Map<String, String> testParams = new HashMap<>();
                    testParams.put("device-id-pk", deviceId);
                    testParams.put("device-name", deviceModel);
                    testParams.put("platform-name", "Android");
                    testParams.put("platform-version", platformVersion);
                    testParams.put("udid", deviceUDID);
                    testParams.put("automation-name", automationName);
                    testParams.put("app-package", appUnderTestPackageName);
                    testParams.put("app-activity", appUnderTestActivityName);
                    testParams.put("wda-system-port", String.valueOf(portNumberStartWDA));

                    // Set parameters of current test
                    deviceTest.setParameters(testParams);

                    // Get XMLClasses from list of test packages
                    List<XmlClass> xmlClasses = getXmlClasses(listOfPackages);

                    // Attach created XMLClasses to current test instance
                    deviceTest.setXmlClasses(xmlClasses);

                    // add current test instance to the list of suite's test
                    suiteTests.add(deviceTest);

                    // Increment WDA Port number
                    portNumberStartWDA++;
                });

                // Add all test tags to current suite instance
                xmlSuite.setTests(suiteTests);

                // Create listeners to add to suite
                List<String> listeners = new ArrayList<>();
                listeners.add("com.epam.reportportal.testng.ReportPortalTestNGListener");

                // Set listener for suite.
                xmlSuite.setListeners(listeners);

                // Add suite to list of suites list
                listOfSuites.add(xmlSuite);

                // Return created suite
                return listOfSuites;
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    private XmlSuite setSuiteRunConfiguration(XmlSuite xmlSuite, XmlSuite.ParallelMode parallelMode) {
        xmlSuite.setParallel(parallelMode);
        return xmlSuite;
    }


    private XmlSuite getXmlSuite(String suiteName) {
        XmlSuite suite = new XmlSuite();
        suite.setName(suiteName);
        return suite;
    }

    private XmlTest getXmlTest(XmlSuite suite, String testName) {
        XmlTest test = new XmlTest(suite);
        test.setName(testName);
        return test;
    }

    private List<XmlClass> getXmlClasses(List<String> listOfClasses) {
        List<XmlClass> classez = new ArrayList<>();
        try {
            listOfClasses.forEach(classFile -> {
                classez.add(new XmlClass(classFile));
            });
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return classez;
    }

    public enum ParallelMode {
        TESTS,
        METHODS,
        CLASSES,
        INSTANCES
    }
}
