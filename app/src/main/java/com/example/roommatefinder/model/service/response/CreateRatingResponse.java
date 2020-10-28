package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Rating;

public class CreateRatingResponse extends Response {

    private Rating rating;

    public CreateRatingResponse(String message) {
        super(false, message);
    }

    public CreateRatingResponse(Rating rating) {
        super(true, null);
        this.rating = rating;
    }
}
