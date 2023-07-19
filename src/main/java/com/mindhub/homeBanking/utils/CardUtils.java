package com.mindhub.homeBanking.utils;

import java.util.Random;

public final class CardUtils {
    private CardUtils(){}
    public static Random randomNumber = new Random();
    public static String getCardNumber() {
        int randomNumber1 = randomNumber.nextInt(9999);
        int randomNumber2 = randomNumber.nextInt(9999);
        int randomNumber3 = randomNumber.nextInt(9999);
        int randomNumber4 = randomNumber.nextInt(9999);
        String randomCardNumber = String.format("%04d-%04d-%04d-%04d", randomNumber1, randomNumber2, randomNumber3, randomNumber4);
        return randomCardNumber;
    }

    public static String getCvvNumber() {
        int randomCvvNumber = randomNumber.nextInt(1000); // Genera un número de hasta 3 dígitos
        String formattedCvvNumber = String.format("%03d", randomCvvNumber);
        return formattedCvvNumber;
    }
}
