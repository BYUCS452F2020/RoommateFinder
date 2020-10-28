package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Rating;
import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.model.service.response.RatingsResponse;

import java.util.ArrayList;
import java.util.List;

public class RatingTable {
    public Rating Create(CreateRatingRequest request) {
        return new Rating(request.getRating().getRatingID(), request.getRating().getUsername(), 3, "comment");
    }

    public Boolean Update(CreateRatingRequest request) {
        return true;
    }

    public Boolean Delete(RatingsRequest request) {
        //probably won't need
        return true;
    }

    public List<Rating> Query(RatingsRequest request) {
        ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(request.getRatingID(), request.getUsername(), 3, "comment"));

        return ratings;
    }
}
