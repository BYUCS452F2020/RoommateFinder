package com.example.roommatefinder.Utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomAuthTokenGenerator {
    public RandomAuthTokenGenerator() {}

    public String generateAuthToken() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

       return generatedString;
    }
}
