package by.epam.m6_t1.logic;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class Security {
    private static final SecretKeySpec key = new SecretKeySpec("spwkfgzcjq=12iq]".getBytes(), "AES");

    public static String encrypt(String encryptData) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(encryptData.getBytes()));

        return encrypted;
    }

    public static String decrypt(String encrypted) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedValue = Base64.getDecoder().decode(encrypted);
        byte[] decryptedValue = cipher.doFinal(decodedValue);

        String decrypted = new String(decryptedValue);
        return decrypted;
    }
}
