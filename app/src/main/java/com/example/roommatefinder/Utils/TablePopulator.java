package com.example.roommatefinder.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.roommatefinder.model.Location;
import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.Rating;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

    public void populatePreferenceTable(int numOfMales, int numOfFemales) {
        for (int i = 0; i < numOfMales; i++) {
            String email = "Guy" + i + "@fakeemail.com";
            Preference preference = new Preference(email, 12, new Random().nextInt(10), new Random().nextInt(10), ThreadLocalRandom.current().nextDouble(100.0, 2000.0),
                    "m", "4 months");
            CreatePreferenceRequest request = new CreatePreferenceRequest(preference);
            try {
                SQLAccess.createPreference(request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < numOfFemales; i++) {
            String email = "Girl" + i + "@fakeemail.com";
            Preference preference = new Preference(email, 12, new Random().nextInt(10), new Random().nextInt(10), ThreadLocalRandom.current().nextDouble(100.0, 2000.0),
                    "f", "7 months");
            CreatePreferenceRequest request = new CreatePreferenceRequest(preference);
            try {
                SQLAccess.createPreference(request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void populateLocationTable(int numOfMaleLocation, int numOfFemaleLocation) {
        for (int i = 0; i < numOfMaleLocation; i++) {
            String email = "Guy" + i + "@fakeemail.com";
            String country = "USA";
            String state = "Utah";
            String city = "SLC";
            String streetName = "street";
            int buildingNumber = new Random().nextInt(2900);
            int apartmentNumber = new Random().nextInt(300);
            Location location = new Location(email, country, state, city, streetName, buildingNumber, apartmentNumber);
            CreateLocationRequest request = new CreateLocationRequest(location);
            try {
                SQLAccess.createLocation(request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < numOfFemaleLocation; i++) {
            String email = "Girl" + i + "@fakeemail.com";
            String country = "USA";
            String state = "Utah";
            String city = "SLC";
            String streetName = "street";
            int buildingNumber = new Random().nextInt(2900);
            int apartmentNumber = new Random().nextInt(300);
            Location location = new Location(email, country, state, city, streetName, buildingNumber, apartmentNumber);
            CreateLocationRequest request = new CreateLocationRequest(location);
            try {
                SQLAccess.createLocation(request);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void populateRatingTable(int numOfMaleRaters, int numOfFemaleRaters) {
        int counter = 0;
        for (int i = 0; i < numOfMaleRaters; i++) {
            String email = "Guy" + (i + 1) + "@fakeemail.com";
            String ratingGiver = "Guy" + i + "@fakeemail.com";
            int score = 0;
            while (score == 0) {
                score = new Random().nextInt(5);
            }
            String comment;
            if (score == 4 || score == 5) {
                comment = "This guy is awesome";
            }
            else if (score == 2 || score == 3) {
                comment = "Not the most friendly guy";
            }
            else {
                comment = "Wouldn't recommend";
            }
            try {
                String ratingID = new RandomPostIDGenerator().getRandomPostID();
                Rating rating = new Rating(ratingID, email, ratingGiver, score, comment);
                SQLAccess.createRating(new CreateRatingRequest(rating));
                System.out.println("Created Rating" + ratingID);
            } catch (SQLException e) {
                e.printStackTrace();
                counter++;
                System.out.println("error");
            }
        }

        for (int i = 0; i < numOfFemaleRaters; i++) {
            String email = "Girl" + (i + 1) + "@fakeemail.com";
            String ratingGiver = "Girl" + i + "@fakeemail.com";
            int score = 0;
            while (score == 0) {
                score = new Random().nextInt(5);
            }
            String comment;
            if (score == 4 || score == 5) {
                comment = "This lady is awesome";
            }
            else if (score == 2 || score == 3) {
                comment = "Not the most friendly lady";
            }
            else {
                comment = "Wouldn't recommend";
            }
            try {
                String ratingID = new RandomPostIDGenerator().getRandomPostID();
                Rating rating = new Rating(ratingID, email, ratingGiver, score, comment);
                SQLAccess.createRating(new CreateRatingRequest(rating));
                System.out.println("Created Rating" + ratingID);
            } catch (SQLException e) {
                e.printStackTrace();
                counter++;
                System.out.println("error");
            }
        }
    }

    public void populatePostingsTable(int numOfMalePostings, int numOfFemalePostings) {
        int counter = 0;
        for (int i = 0; i < numOfMalePostings; i++) {
            String email = "Guy" + i + "@fakeemail.com";
            int vacancy = 0;
            while (vacancy == 0) {
                vacancy = new Random().nextInt(6);
            }
            String postContent = "Looking for " + vacancy + " to fill this beautiful home";
            Posting posting = new Posting(new RandomPostIDGenerator().getRandomPostID(), email,  postContent, vacancy);
            CreatePostingRequest request = new CreatePostingRequest(posting);
            try {
                SQLAccess.insertPostingIntoPostingTable(request);
                counter++;
                System.out.println("Counter is " + counter);
            } catch (SQLException e) {
                e.printStackTrace();
                counter++;
                System.out.println("Counter is " + counter);
            }
        }

        for (int i = 0; i < numOfFemalePostings; i++) {
            String email = "Girl" + i + "@fakeemail.com";
            int vacancy = 0;
            while (vacancy == 0) {
                vacancy = new Random().nextInt(6);
            }
            String postContent = "Looking for " + vacancy + " to fill this beautiful home";
            Posting posting = new Posting(new RandomPostIDGenerator().getRandomPostID(), email,  postContent, vacancy);
            CreatePostingRequest request = new CreatePostingRequest(posting);
            try {
                SQLAccess.insertPostingIntoPostingTable(request);
                counter++;
                System.out.println("Counter is " + counter);
            } catch (SQLException e) {
                e.printStackTrace();
                counter++;
                System.out.println("Counter is " + counter);
            }
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
