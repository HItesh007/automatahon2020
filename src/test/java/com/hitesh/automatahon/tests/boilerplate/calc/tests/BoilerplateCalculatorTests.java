package com.hitesh.automatahon.tests.boilerplate.calc.tests;

import com.hitesh.automatahon.tests.boilerplate.calc.pages.CalculatorPage;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoilerplateCalculatorTests {
    private static final Logger logger = LogManager.getLogger(BoilerplateCalculatorTests.class.getName());

    @Test(priority = 0)
    public void additionTest() {
        CalculatorPage calculatorPage = new CalculatorPage();

        String finalResult = calculatorPage.add(10, 10, 10, 10);
        logger.info("Final Result : " + finalResult);

        Assert.assertEquals(NumberUtils.toInt(finalResult), 40, "Final Result did not match expected value");
    }
}
