package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Rating;
import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.request.RatingRequest;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.model.service.response.RatingResponse;

public class RatingTable {
    public Rating Create(CreateRatingRequest request) {
        return new Rating(request.getRating().getRatingID(), request.getRating().getUsername(), 3, "comment");
    }

    public Boolean Update(CreateRatingRequest request) {
        return true;
    }

    public Boolean Delete(RatingRequest request) {
        //probably won't need
        return true;
    }

    public Rating Query(RatingRequest request) {
        return new Rating(request.getRatingID(), request.getUsername(), 3, "comment");
    }
}
