package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.RatingsService;
import com.example.roommatefinder.model.service.request.RatingsRequest;
import com.example.roommatefinder.model.service.response.RatingsResponse;
import com.example.roommatefinder.net.asynctasks.RatingsTaskFacade;

import java.io.IOException;

public class RatingsPresenter implements RatingsTaskFacade.Observer {

    private final View view;

    @Override
    public void onRatingsResponseReceived(RatingsResponse response) {
        view.onRatingsResult(response);
    }

    public interface View {
        void onRatingsResult(RatingsResponse response);
    }

    public RatingsPresenter(View view){
        this.view = view;
    }

    public void getRatings(RatingsRequest request) throws IOException {
        RatingsService ratingsService = getRatingsService();
        ratingsService.getRatings(request, this);
    }

    public RatingsService getRatingsService() {
        return new RatingsService();
    }
}
