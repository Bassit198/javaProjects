package org.example.passwordmanager.Services;

public class Encryptor {

    public static String encrypt(String text) {
        int key = 6;
        char[] chars = text.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if(i%2==0) {
                chars[i]+=key;
            }else {
                chars[i]-=key;
            }

        }
        String encrypt = String.copyValueOf(chars);
        return encrypt;

    }

    public static String decrypt(String text) {
        int key = 6;
        char[] chars = text.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if(i%2==0) {
                chars[i]-=key;
            }else {
                chars[i]+=key;
            }

        }
        String decrypt = String.copyValueOf(chars);
        return decrypt;

    }
}
