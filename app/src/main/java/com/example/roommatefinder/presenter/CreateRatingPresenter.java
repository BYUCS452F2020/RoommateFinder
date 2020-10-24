package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.service.CreateRatingService;
import com.example.roommatefinder.model.service.request.CreateRatingRequest;
import com.example.roommatefinder.model.service.response.CreateRatingResponse;
import com.example.roommatefinder.net.asynctasks.CreateRatingTaskFacade;

import java.io.IOException;

public class CreateRatingPresenter implements CreateRatingTaskFacade.Observer {

    private final View view;

    @Override
    public void onCreateRatingResponseReceived(CreateRatingResponse response) {
        view.onCreateRatingResult(response);
    }

    public interface View {
        void onCreateRatingResult(CreateRatingResponse response);
    }

    public CreateRatingPresenter(View view) {
        this.view = view;
    }

    public void createRating(CreateRatingRequest request) throws IOException {
        CreateRatingService ratingService = getRatingService();
        ratingService.createRating(request, this);
    }

    public CreateRatingService getRatingService() {
        return new CreateRatingService();
    }
}
