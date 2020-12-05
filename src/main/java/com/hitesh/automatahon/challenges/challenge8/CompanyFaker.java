package com.hitesh.automatahon.challenges.challenge8;

import com.github.javafaker.Faker;
import org.apache.commons.lang.WordUtils;

import java.util.Random;

public class CompanyFaker {
    public static String getCompanyName() {
        return WordUtils
                .capitalizeFully(
                        new Faker(new Random())
                                .company()
                                .name()
                );
    }

    public static String getCompanyPhone() {
        return new Faker(new Random())
                .phoneNumber()
                .cellPhone();
    }

    public static String getCompanyEmail() {
        return new Faker(new Random())
                .internet()
                .safeEmailAddress();
    }
}
