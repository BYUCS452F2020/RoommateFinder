package com.example.roommatefinder.model.service.request;

public class RatingsRequest extends Request {
    //Query
    private String ratingID;//we won't ever know the ratingID
    private String email;

    public RatingsRequest(String ratingID, String email) {
        this.email = email;
        this.ratingID = ratingID;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getRatingID() {
        return ratingID;
    }

    public void setRatingID(String ratingID) {
        this.ratingID = ratingID;
    }
}
