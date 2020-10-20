package com.example.roommatefinder.model;

public class Posting {

    private String country;
    private String state;
    private String city;
    private String street;
    private int buildNum;
    private int roomNum;

    public Posting(String country, String state, String city, String street, int buildNum, int roomNum){
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.buildNum = buildNum;
        this.roomNum = roomNum;
    }

    public String getCity() {
        return city;
    }

    public int getBuildNum() {
        return buildNum;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public void setBuildNum(int buildNum) {
        this.buildNum = buildNum;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

