package com.example.roommatefinder.model.service.request;

public class RegisterRequest extends Request {

    private String firstName;
    private String lastName;
    private Character gender;
    private int age;
    private String email;
    private String password;
    private String phoneNumber;

    public RegisterRequest(String firstName, String lastName, Character gender, int age, String email,
                           String password, String phoneNumber){
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

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public Character getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

}
