package com.example.synq;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class PassEncrypt {

    private static final String AES_ALGORITHM = "AES";
    private static final String HMAC_ALGORITHM = "HmacSHA256";

    public static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    public static String encryptPassword(String password, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    public static String hashWithHMAC(String encryptedPassword, byte[] salt) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(salt, HMAC_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        mac.init(keySpec);
        byte[] hmacHash = mac.doFinal(encryptedPassword.getBytes());
        return Base64.getEncoder().encodeToString(hmacHash);
    }

    public static String finalEncrypt(String password) throws Exception {
        SecretKey aesKey = generateAESKey();
        String encryptedPassword = encryptPassword(password, aesKey);
        System.out.println("Encrypted Password: " + encryptedPassword);
        byte[] salt = generateSalt();
        return hashWithHMAC(encryptedPassword, salt);
    }
}
