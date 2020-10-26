package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.CreatePostingService;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.response.CreatePostingResponse;
import com.example.roommatefinder.net.asynctasks.CreatePostingTaskFacade;

import java.io.IOException;

public class CreatePostingPresenter implements CreatePostingTaskFacade.Observer {

    private final View view;

    @Override
    public void onCreatePostingResponseReceived(CreatePostingResponse response) {
        view.onCreatePostingResult(response);
    }

    public interface View {
        void onCreatePostingResult(CreatePostingResponse response);
    }

    public CreatePostingPresenter(View view) {
        this.view = view;
    }

    public void createPosting(CreatePostingRequest request) throws IOException {
        CreatePostingService createPostingService = getCreatePostingService();
        createPostingService.createPosting(request, this);
    }

    public CreatePostingService getCreatePostingService() {
        return new CreatePostingService();
    }
}
