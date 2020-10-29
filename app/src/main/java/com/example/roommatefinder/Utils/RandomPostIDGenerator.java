package com.example.roommatefinder.Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPostIDGenerator {
    public RandomPostIDGenerator() {}

    public String getRandomPostID() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
    }
}
