package com.example.roommatefinder.model;

public class Location {
    private String email;
    private String country;
    private String state;
    private String city;
    private String streetName;
    private int buildingNumber;
    private int roomNumber;

    public Location(String email, String country, String state, String city, String streetName, int buildingNumber, int roomNumber) {
        this.email = email;
        this.country = country;
        this.state = state;
        this.city = city;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.roomNumber = roomNumber;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}


