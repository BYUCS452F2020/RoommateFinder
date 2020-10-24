package com.example.roommatefinder.net.asynctasks;

import android.os.AsyncTask;

import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.net.DBDAO.RatingTable;

public class CreateRatingTaskFacade extends AsyncTask<CreateRatingRequest, Void, CreateRatingResponse> {

    private Observer observer;

    public interface Observer {
        public void onCreateRatingResponseReceived(CreateRatingResponse response);
    }

    public CreateRatingTaskFacade (Observer observer) {
        this.observer = observer;
    }

    @Override
    protected CreateRatingResponse doInBackground(CreateRatingRequest ... requests) {
        //create rating
        RatingTable ratingTable = new RatingTable();
        CreateRatingResponse response = ratingTable.Create(requests[0]);
        return response;
    }
}
