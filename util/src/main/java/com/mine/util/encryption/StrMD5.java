package com.mine.util.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class StrMD5 {
    private static StrMD5 instance;
    private static Object keyObject = new Object();

    private StrMD5() {
    }

    public static StrMD5 getInstance() {
        if (instance == null) {
            synchronized (keyObject) {
                if (null == instance)
                    instance = new StrMD5();
            }
        }
        return instance;
    }

    static private byte[] loadBytes(String name) {
        byte[] buffer = name.getBytes();
        return ( buffer );
    }

    static private byte[] loadBytes(String name, String charset) {
        byte[] buffer = null;
        try {
            buffer = name.getBytes(charset);
        } catch (Exception e) {
        }

        return ( buffer );
    }

    public static void main(String[] args) {
        System.out.println(StrMD5.getInstance().getStringMD5("12345678"));
        String s = "25d55ad283aa400af464c76d713c07ad";
    }

    public String getStringMD5(String key) {
        try {
            MessageDigest currentAlgorithm = MessageDigest.getInstance("MD5");
            return computeDigest(currentAlgorithm, loadBytes(key));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available.", e);
        }
    }

    public String getMD5Str(String key, String charset) {
        try {
            MessageDigest currentAlgorithm = MessageDigest.getInstance("MD5");
            return computeDigest(currentAlgorithm, loadBytes(key, charset));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not available.", e);
        }
    }

    private String computeDigest(MessageDigest currentAlgorithm, byte[] b) {
        currentAlgorithm.reset();
        currentAlgorithm.update(b);
        byte[] hash = currentAlgorithm.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; i += 2) {   // format with 2-byte words with spaces.
            sb.append(getHexString(hash[ i ]));
            sb.append(getHexString(hash[ i + 1 ]));
        }
        return sb.toString().trim().toLowerCase();
    }

    private String getHexString(byte value) {
        int usbyte = value & 0xFF;// byte-wise AND converts signed byte to unsigned.
        StringBuilder sb = new StringBuilder();
        if (usbyte < 16)
            sb.append("0");
        sb.append(Integer.toHexString(usbyte));// pad on left if single hex digit.
        return sb.toString();
    }
}