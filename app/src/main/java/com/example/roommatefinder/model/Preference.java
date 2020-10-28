package com.example.roommatefinder.model;

public class Preference {
    private String email;
    private int preferredTimeToSleep; //stored as military time but displayed in 12 hr time
    private int socialLevel; //From a scale of 1-10
    private int cleanlinessLevel; //Scale 1-10
    private double price;
    private String type; //male only contract?
    private String lengthOfContract;

    public Preference(String email, int preferredTimeToSleep, int socialLevel, int cleanlinessLevel, double price, String type, String lengthOfContract) {
        this.email = email;
        this.preferredTimeToSleep = preferredTimeToSleep;
        this.socialLevel = socialLevel;
        this.cleanlinessLevel = cleanlinessLevel;
        this.price = price;
        this.type = type;
        this.lengthOfContract = lengthOfContract;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPreferredTimeToSleep() {
        return preferredTimeToSleep;
    }

    public void setPreferredTimeToSleep(int preferredTimeToSleep) {
        this.preferredTimeToSleep = preferredTimeToSleep;
    }

    public int getSocialLevel() {
        return socialLevel;
    }

    public void setSocialLevel(int socialLevel) {
        this.socialLevel = socialLevel;
    }

    public int getCleanlinessLevel() {
        return cleanlinessLevel;
    }

    public void setCleanlinessLevel(int cleanlinessLevel) {
        this.cleanlinessLevel = cleanlinessLevel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLengthOfContract() {
        return lengthOfContract;
    }

    public void setLengthOfContract(String lengthOfContract) {
        this.lengthOfContract = lengthOfContract;
    }
}
