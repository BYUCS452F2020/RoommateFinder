package com.example.roommatefinder.model.service.request;

public class RatingRequest {
    //Query
    private String ratingID;//we won't ever know the ratingID
    private String username;

    public RatingRequest(String ratingID, String username) {
        this.username = username;
        this.ratingID = ratingID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRatingID() {
        return ratingID;
    }

    public void setRatingID(String ratingID) {
        this.ratingID = ratingID;
    }
}
