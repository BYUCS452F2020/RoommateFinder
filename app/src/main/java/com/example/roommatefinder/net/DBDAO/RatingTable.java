package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.request.RatingRequest;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.model.service.response.RatingResponse;

public class RatingTable implements DAOInterface<CreateRatingRequest, CreateRatingResponse, RatingRequest, RatingResponse>{
    @Override
    public CreateRatingResponse Create(CreateRatingRequest request) {
        return null;
    }

    @Override
    public CreateRatingResponse Update(CreateRatingRequest request) {
        return null;
    }

    @Override
    public RatingResponse Delete(RatingRequest request) {
        //probably won't need
        return null;
    }

    @Override
    public RatingResponse Query(RatingRequest request) {
        return null;
    }
}
