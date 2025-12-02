package com.wojtek.walczak.eljot.cepik;

import java.util.regex.Pattern;

public class PolishDrivingLicenseValidator {

    private static final Pattern LICENSE_PATTERN =
            Pattern.compile("^[A-Z][A-Z0-9]{7}$");

    public static boolean isValid(String number) {
        if (number == null) {
            return false;
        }

        // jeśli nie chcesz przycinać spacji, usuń to
        String trimmed = number.trim();

        return LICENSE_PATTERN.matcher(trimmed).matches();
    }
}
