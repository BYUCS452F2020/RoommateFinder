package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.CreatePostingService;
import com.example.roommatefinder.model.service.CreatePreferenceService;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePostingResponse;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.net.asynctasks.CreatePreferenceTaskFacade;

import java.io.IOException;

public class CreatePreferencePresenter implements CreatePreferenceTaskFacade.Observer {
    private final CreatePreferencePresenter.View view;

    @Override
    public void onCreatePreferenceResponseReceived(CreatePreferenceResponse response) {
        view.onCreatePreferenceResult(response);
    }

    public interface View {
        void onCreatePreferenceResult(CreatePreferenceResponse response);
    }

    public CreatePreferencePresenter(CreatePreferencePresenter.View view) {
        this.view = view;
    }

    public void createPreference(CreatePreferenceRequest request) throws IOException {
        CreatePreferenceService createPreferenceService = getCreatePostingService();
        createPreferenceService.createPreference(request, this);
    }

    public CreatePreferenceService getCreatePostingService() {
        return new CreatePreferenceService();
    }
}
