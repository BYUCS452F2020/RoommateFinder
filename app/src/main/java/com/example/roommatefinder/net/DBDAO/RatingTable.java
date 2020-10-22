package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Rating;
import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.request.RatingRequest;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.model.service.response.RatingResponse;

public class RatingTable implements DAOInterface<CreateRatingRequest, CreateRatingResponse, RatingRequest, RatingResponse>{
    @Override
    public CreateRatingResponse Create(CreateRatingRequest request) {
        return new CreateRatingResponse(true);
    }

    @Override
    public CreateRatingResponse Update(CreateRatingRequest request) {
        return new CreateRatingResponse(true);
    }

    @Override
    public RatingResponse Delete(RatingRequest request) {
        //probably won't need
        return new RatingResponse(new Rating(request.getRatingID(), request.getUsername(), 3, "comment"));
    }

    @Override
    public RatingResponse Query(RatingRequest request) {
        return new RatingResponse(new Rating(request.getRatingID(), request.getUsername(), 3, "comment"));
    }
}
