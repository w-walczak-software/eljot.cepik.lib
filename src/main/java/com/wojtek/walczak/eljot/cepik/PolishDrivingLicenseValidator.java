package com.wojtek.walczak.eljot.cepik;

import java.util.regex.Pattern;

public class PolishDrivingLicenseValidator {

    // podstawowy wzorzec: 10 znaków A-Z lub 0-9
    private static final Pattern BASE_PATTERN =
            Pattern.compile("^[A-Z0-9]{8}$");


    public static boolean isValid(String number) {
        if (number == null) {
            return false;
        }

        // usuwamy spacje i myślniki, zamieniamy na wielkie litery
        String normalized = number.trim()
                .replaceAll("[\\s-]", "")
                .toUpperCase();

        // 1) podstawowy pattern
        if (!BASE_PATTERN.matcher(normalized).matches()) {
            return false;
        }

        // 2) pierwszy znak musi być literą
        char first = normalized.charAt(0);
        if (!Character.isLetter(first)) {
            return false;
        }

        // 3) 2–3 litery na początku
        int prefixLetters = 0;
        for (int i = 0; i < normalized.length(); i++) {
            if (Character.isLetter(normalized.charAt(i))) {
                prefixLetters++;
            } else {
                break;
            }
        }

        if (prefixLetters < 2 || prefixLetters > 3) {
            return false;
        }

        // 4) numer nie może składać się wyłącznie z cyfr
        boolean allDigits = true;
        for (int i = 0; i < normalized.length(); i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                allDigits = false;
                break;
            }
        }
        if (allDigits) {
            return false;
        }

        // 5) nie może zaczynać się od "POL"
        if (normalized.startsWith("POL")) {
            return false;
        }

        return true;
    }
}
