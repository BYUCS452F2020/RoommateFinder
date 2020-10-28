package com.example.roommatefinder.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;
import java.util.Random;

public class TablePopulator {

    public void deleteAllAuthTokens() {
        try {
            SQLAccess.deleteAllAuthToken();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUsers() {
        try {
            SQLAccess.deleteAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void populateUserTable(int numOfMaleUsers, int numOfFemaleUsers) {
        int counter = 0;
        for (int i = 0; i < numOfMaleUsers; i++) {
            String firstName = "GuyFirstName" + i;
            String lastName = "GuyLastName" + i;
            String email = "Guy" + i + "@fakeemail.com";
            int age = new Random().nextInt(100);
            Character gender = 'm';
            String password = "password" + i;
            String phoneNumber = "1234567890";

            RegisterRequest request = new RegisterRequest(firstName,lastName, gender, age, email,
                     password,  phoneNumber);
            try {
                password = new PasswordHasher(password).getHashPassword();
                request.setPassword(password);
                SQLAccess.addEntryToUserTable(request);
                counter++;
                System.out.println("Wrote user" + email + " to database");
                System.out.println("Count: " + counter);
            } catch (SQLException e) {
                e.printStackTrace();
                counter++;
            }
        }
        for (int i = 0; i < numOfFemaleUsers; i++) {
            String firstName = "GirlFirstName" + i;
            String lastName = "GirlLastName" + i;
            String email = "Girl" + i + "@fakeemail.com";
            int age = new Random().nextInt(100);
            Character gender = 'f';
            String password = "password"+i;
            String phoneNumber = "1234567890";

            RegisterRequest request = new RegisterRequest(firstName,lastName, gender, age, email,
                    password,  phoneNumber);
            try {
                password = new PasswordHasher(password).getHashPassword();
                request.setPassword(password);
                SQLAccess.addEntryToUserTable(request);
                counter++;
                System.out.println("Wrote user" + email + " to database");
                System.out.println("Count: " + counter);
            } catch (SQLException e) {
                e.printStackTrace();
                counter++;
            }
        }
    }

}
