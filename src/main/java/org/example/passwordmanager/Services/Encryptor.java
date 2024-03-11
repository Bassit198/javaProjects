package org.example.passwordmanager.Services;

public class Encryptor {

    public static String encrypt(String text) {
        char[] chars = text.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if(i%2==0) {
                chars[i]+=6;
            }else {
                chars[i]-=9;
            }

        }
        return String.copyValueOf(chars);

    }

    public static String decrypt(String text) {
        char[] chars = text.toCharArray();
        for(int i=0; i<chars.length; i++) {
            if(i%2==0) {
                chars[i]-=6;
            }else {
                chars[i]+=9;
            }

        }
        return String.copyValueOf(chars);

    }


    //BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    //String hashPassword = bcrypt.encode(memberInfo.getPassword());
}
