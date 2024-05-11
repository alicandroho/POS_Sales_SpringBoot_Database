package com.pos_sales;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class TokenGenerator {

    public static String generateToken() {
        try {
            // Use SecureRandom for a secure random number generator
            SecureRandom random = SecureRandom.getInstanceStrong();

            // Generate a random byte array
            byte[] randomBytes = new byte[32];
            random.nextBytes(randomBytes);

            // Use MessageDigest to hash the random bytes
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(randomBytes);

            // Convert the hashed bytes to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
}
