package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.PreferenceService;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.net.asynctasks.PreferenceTaskFacade;

import java.io.IOException;

public class PreferencePresenter implements PreferenceTaskFacade.Observer {

    private final View view;

    @Override
    public void onPreferenceResponseReceived(PreferenceResponse response){
        view.onPreferenceResult(response);
    }

    public interface View {
        void onPreferenceResult(PreferenceResponse response);
    }

    public PreferencePresenter(View view) {
        this.view = view;
    }

    public void getPreference(PreferenceRequest request) throws IOException {
        PreferenceService preferenceService = getPreferenceService();
        preferenceService.getPreference(request, this);
    }

    public PreferenceService getPreferenceService() {
        return new PreferenceService();
    }
}
