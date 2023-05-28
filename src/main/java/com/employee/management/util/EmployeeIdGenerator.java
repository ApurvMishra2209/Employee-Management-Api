package com.employee.management.util;

import java.util.Random;

public class EmployeeIdGenerator {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final int ID_LENGTH = 6;

    public static String generateUniqueId() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        Random random = new Random();
        sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));

        sb.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));

        for (int i = 0; i < ID_LENGTH - 2; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return sb.toString();
    }
}

