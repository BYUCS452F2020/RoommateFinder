package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.Rating;
import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.model.service.response.RatingsResponse;
import com.example.roommatefinder.net.DBDAO.RatingTable;

import java.util.ArrayList;
import java.util.List;

public class RatingsTaskFacade extends AsyncTask<RatingsRequest, Void, RatingsResponse> {

    private Observer observer;

    public interface Observer {
        public void onRatingsResponseReceived(RatingsResponse response);
    }

    public RatingsTaskFacade(Observer observer) {
        this.observer = observer;
    }

    @Override
    protected RatingsResponse doInBackground(RatingsRequest ... requests) {
        RatingTable ratingTable = new RatingTable();
        //TODO: Make sure that this eventually returns all ratings relating to a user when requested.
        List<Rating> ratings = ratingTable.Query(requests[0]);
        RatingsResponse response = null;
        if (ratings != null) {
            response = new RatingsResponse(ratings);
        }
        else {
            response = new RatingsResponse("No list of ratings was returned.");
        }
        
        return response;
    }

    @Override
    protected void onPostExecute(RatingsResponse ratingsResponse){
        super.onPostExecute(ratingsResponse);

        observer.onRatingsResponseReceived(ratingsResponse);
    }
}
