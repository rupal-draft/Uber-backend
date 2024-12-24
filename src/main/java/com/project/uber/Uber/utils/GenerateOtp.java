package com.project.uber.Uber.utils;

import java.util.Random;

public class GenerateOtp {

    public static String generateOtp() {
        Random random = new Random();
        int otp = random.nextInt(10000);
        return String.format("%04d", otp);
    }
}
