package com.example.roommatefinder.model.service.request;

import com.example.roommatefinder.model.Rating;

public class CreateRatingRequest extends Request {
    private Rating rating;

    public CreateRatingRequest(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
