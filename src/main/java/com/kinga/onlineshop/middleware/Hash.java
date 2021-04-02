package com.kinga.onlineshop.middleware;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Hash {
    public static String hash(String password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        char[] charPassword = password.toCharArray();
        String hash;
        try{
            hash = argon2.hash(22, 65536, 1, charPassword);
        } finally{
            argon2.wipeArray(charPassword);
        }
        return hash;
    }

    public static boolean verify(String hash, String password){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        char[] charPassword = password.toCharArray();
        return argon2.verify(hash, charPassword);
    }
}
