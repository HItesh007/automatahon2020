package com.hitesh.automatahon.tests.boilerplate.calc.pages;

import com.hitesh.automatahon.helper.appium.AppiumEventHelpers;
import com.hitesh.automatahon.helper.appium.AppiumSyncHelper;
import com.hitesh.automatahon.tests.boilerplate.calc.enums.Calculator;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CalculatorPage {

    By calculatorMainViewBy = MobileBy.id("com.google.android.calculator:id/main_calculator");
    By numericPadBy = MobileBy.id("com.google.android.calculator:id/pad_numeric");
    By calcResultViewBy = MobileBy.id("com.google.android.calculator:id/result_preview");
    By resultFinalViewBy = MobileBy.id("com.google.android.calculator:id/result_final");
    By zeroDigitBy = MobileBy.id("com.google.android.calculator:id/digit_0");
    By oneDigitBy = MobileBy.id("com.google.android.calculator:id/digit_1");
    By twoDigitBy = MobileBy.id("com.google.android.calculator:id/digit_2");
    By threeDigitBy = MobileBy.id("com.google.android.calculator:id/digit_3");
    By fourDigitBy = MobileBy.id("com.google.android.calculator:id/digit_4");
    By fiveDigitBy = MobileBy.id("com.google.android.calculator:id/digit_5");
    By sixDigitBy = MobileBy.id("com.google.android.calculator:id/digit_6");
    By sevenDigitBy = MobileBy.id("com.google.android.calculator:id/digit_7");
    By eightDigitBy = MobileBy.id("com.google.android.calculator:id/digit_8");
    By nineDigitBy = MobileBy.id("com.google.android.calculator:id/digit_9");
    By decimalPointBy = MobileBy.id("com.google.android.calculator:id/dec_point");
    By equalsBy = MobileBy.id("com.google.android.calculator:id/eq");
    By clearBy = MobileBy.id("com.google.android.calculator:id/clr");
    By divisionBy = MobileBy.id("com.google.android.calculator:id/op_div");
    By multiplyBy = MobileBy.id("com.google.android.calculator:id/op_mul");
    By minusBy = MobileBy.id("com.google.android.calculator:id/op_sub");
    By plusBy = MobileBy.id("com.google.android.calculator:id/op_add");

    public CalculatorPage() {
        // To Do
        // Wait until Calculator is loaded
        new AppiumSyncHelper()
                .getWebDriverWait(90)
                .until(ExpectedConditions.visibilityOfElementLocated(numericPadBy));

    }

    public String add(int... valuesToAdd) {
        int parameterSize = valuesToAdd.length;
        int addCounter = 1;
        for (int value : valuesToAdd) {
            // Convert to String
            String number = String.valueOf(value);
            char[] digits = number.toCharArray();

            for (char digit : digits) {
                // Enter Digit
                performClick(digit);
            }

            if (addCounter != parameterSize) {
                AppiumEventHelpers
                        .getInstance()
                        .click(plusBy);
            } else {
                AppiumEventHelpers
                        .getInstance()
                        .click(equalsBy);
            }
            addCounter++;
        }

        // Get Result from final result view
        return AppiumEventHelpers
                .getInstance()
                .getText(resultFinalViewBy);
    }

    private void performClick(char digit) {
        Calculator literalEnum = Calculator.getEnum(digit);

        switch (literalEnum) {
            case ONE:
                AppiumEventHelpers
                        .getInstance()
                        .click(oneDigitBy);
                break;

            case TWO:
                AppiumEventHelpers
                        .getInstance()
                        .click(twoDigitBy);
                break;

            case THREE:
                AppiumEventHelpers
                        .getInstance()
                        .click(threeDigitBy);
                break;

            case FOUR:
                AppiumEventHelpers
                        .getInstance()
                        .click(fourDigitBy);
                break;

            case FIVE:
                AppiumEventHelpers
                        .getInstance()
                        .click(fiveDigitBy);
                break;

            case SIX:
                AppiumEventHelpers
                        .getInstance()
                        .click(sixDigitBy);
                break;

            case SEVEN:
                AppiumEventHelpers
                        .getInstance()
                        .click(sevenDigitBy);
                break;

            case EIGHT:
                AppiumEventHelpers
                        .getInstance()
                        .click(eightDigitBy);
                break;

            case NINE:
                AppiumEventHelpers
                        .getInstance()
                        .click(nineDigitBy);
                break;

            case ZERO:
                AppiumEventHelpers
                        .getInstance()
                        .click(zeroDigitBy);
                break;

            case DECIMAL_POINT:
                AppiumEventHelpers
                        .getInstance()
                        .click(decimalPointBy);
                break;
        }
    }

}
