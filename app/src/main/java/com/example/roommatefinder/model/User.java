package com.example.roommatefinder.model;

import androidx.annotation.Nullable;

public class User {
    private String firstName;
    private String lastName;
    private Character gender;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;

    public User (String firstName, String lastName, Character gender, int age, String email, String password, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Character getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        User inUser = (User) obj;
        if (this.age != inUser.age) {
            return false;
        }
        if (!this.password.equals(inUser.password)) {
            return false;
        }
        if (!this.email.equals(inUser.email)) {
            return false;
        }
        if (!this.firstName.equals(inUser.firstName)) {
            return false;
        }
        if (!this.lastName.equals(inUser.lastName)) {
            return false;
        }
        if (!this.gender.equals(inUser.gender)) {
            return false;
        }
        if (!this.phoneNumber.equals(inUser.phoneNumber)) {
            return false;
        }
        return true;
    }
}
