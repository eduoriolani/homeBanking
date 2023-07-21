package com.mindhub.homeBanking.utils;

import java.util.Random;

public final class AccountUtils {

    private AccountUtils(){}

    public static Random randomNumber = new Random();

    public static String getRandomNum() {
        int randomNum = randomNumber.nextInt(90000000);
        String formatRandomNum = "VIN-" + String.format("%04d", randomNum);
        return formatRandomNum;
    }
}
