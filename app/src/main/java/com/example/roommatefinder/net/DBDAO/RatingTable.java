package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Rating;
import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.model.service.response.RatingsResponse;

import java.util.ArrayList;

public class RatingTable implements DAOInterface<CreateRatingRequest, CreateRatingResponse, RatingsRequest, RatingsResponse>{
    @Override
    public CreateRatingResponse Create(CreateRatingRequest request) {
        return new CreateRatingResponse(true);
    }

    @Override
    public CreateRatingResponse Update(CreateRatingRequest request) {
        return new CreateRatingResponse(true);
    }

    @Override
    public RatingsResponse Delete(RatingsRequest request) {
        //probably won't need
        ArrayList<Rating> ratings = new ArrayList<>();

        ratings.add(new Rating(request.getRatingID(), request.getUsername(), 3, "comment"));

        return new RatingsResponse(ratings);
    }

    @Override
    public RatingsResponse Query(RatingsRequest request) {

        ArrayList<Rating> ratings = new ArrayList<>();

        ratings.add(new Rating(request.getRatingID(), request.getUsername(), 3, "comment"));

        return new RatingsResponse(ratings);
    }
}
