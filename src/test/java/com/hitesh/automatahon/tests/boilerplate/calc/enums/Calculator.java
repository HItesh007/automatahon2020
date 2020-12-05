package com.hitesh.automatahon.tests.boilerplate.calc.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum Calculator {
    ZERO('0'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    DECIMAL_POINT('.'),
    EQUAL('=');

    private static final Map<Character, Calculator> enumMAP;

    static {
        Map<Character, Calculator> mainNavMap = Arrays
                .stream(values())
                .collect(toMap(cg -> cg.literal, e -> e));

        enumMAP = Collections.unmodifiableMap(mainNavMap);
    }

    private final char literal;


    Calculator(char calLiteral) {
        this.literal = calLiteral;
    }

    public static Calculator getEnum(char literal) {
        return enumMAP.get(literal);
    }

    public char getLiteral() {
        return literal;
    }

}
