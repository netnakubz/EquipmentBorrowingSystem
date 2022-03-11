package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;


import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

@Service
public class AES {

    private static byte[] encrypted;

    private static String encryptedtext;
    private static String decrypted;

    public static String Encrypt(String pInput) {

        try {
            String Input = pInput;
            String key = "Bar12345Bar12345Bar12345Bar12345";

            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(Input.getBytes());
            encryptedtext = DatatypeConverter.printBase64Binary(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedtext;
    }

    public static String Decrypt(String pInput) {

        try {

            String Input = pInput;

            String key = "Bar12345Bar12345Bar12345Bar12345";

            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            encrypted = DatatypeConverter.parseBase64Binary(Input);
            decrypted = new String(cipher.doFinal(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypted;

    }

}
