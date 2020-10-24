package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Rating;

import java.util.List;

public class RatingsResponse {
    private List<Rating> ratings;

    public RatingsResponse(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
