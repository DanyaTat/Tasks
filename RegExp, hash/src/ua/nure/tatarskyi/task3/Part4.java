package ua.nure.tatarskyi.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Part4 {

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] hash = digest.digest();
        StringBuilder tempSB = new StringBuilder("");
        for (byte b: hash) {
            String  temp = Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
            int firstNum = Integer.parseInt(temp.substring(0,4), 2);
            int secondNum = Integer.parseInt(temp.substring(4,8), 2);
            tempSB.append(Integer.toHexString(firstNum));
            tempSB.append(Integer.toHexString(secondNum));
        }
        return tempSB.toString().toUpperCase(Locale.ENGLISH);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("asdf", "MD5"));
        System.out.println(hash("asdf", "SHA-256"));
    }
}