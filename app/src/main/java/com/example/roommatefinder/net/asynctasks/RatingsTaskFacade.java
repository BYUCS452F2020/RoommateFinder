package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.model.service.response.RatingsResponse;
import com.example.roommatefinder.net.DBDAO.RatingTable;

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
        RatingsResponse response = ratingTable.Query(requests[0]);

        return response;
    }

    @Override
    protected void onPostExecute(RatingsResponse ratingsResponse){
        super.onPostExecute(ratingsResponse);

        observer.onRatingsResponseReceived(ratingsResponse);
    }
}
