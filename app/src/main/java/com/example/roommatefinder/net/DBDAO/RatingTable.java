package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Rating;
import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.model.service.response.RatingsResponse;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingTable {
    public CreateRatingResponse Create(CreateRatingRequest request) {
        try{
            return SQLAccess.createRating(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return new CreateRatingResponse("Failed to create rating: " + e.getMessage());
        }

        //return new Rating(request.getRating().getRatingID(), request.getRating().getUsername(), 3, "comment");
    }

    public Boolean Update(CreateRatingRequest request) {
        return true;
    }

    public Boolean Delete(RatingsRequest request) {
        //probably won't need
        try{
            return SQLAccess.deleteRating(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public RatingsResponse Query(RatingsRequest request) {
        try{
            return SQLAccess.queryRatings(request);
        }
        catch (SQLException e){
            e.printStackTrace();
            return new RatingsResponse("Failed to return list of ratings: " + e.getMessage());
        }
        /*ArrayList<Rating> ratings = new ArrayList<>();
        ratings.add(new Rating(request.getRatingID(), request.getUsername(), 3, "comment"));

        return ratings;*/
    }
}
