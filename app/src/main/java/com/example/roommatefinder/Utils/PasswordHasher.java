package com.example.roommatefinder.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Random;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {
    private String password;

    public PasswordHasher(String password) {
        this.password = password;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getHashPassword() {
        byte[] salt = new byte[16];
        Random random = new Random(25);
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(("PBKDF2WithHmacSHA1"));
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Base64.Encoder encoder = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                encoder = Base64.getEncoder();
            }
            String encodedHash = encoder.encodeToString(hash);
            return encodedHash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean authenticate(String hashedPasswordFromTable) {
        //encode password and check
        String proposedHashed = getHashPassword();
        if (proposedHashed.equals(hashedPasswordFromTable)) {
            return true;
        } else {
            return false;
        }
    }
}