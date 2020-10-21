package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Rating;

public class RatingResponse {
    private Rating rating;

    public RatingResponse(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
