package com.example.roommatefinder.model;

public class User implements Comparable<User> {

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

    @Override
    public int compareTo(User o) {
        return 0;
    }
}
