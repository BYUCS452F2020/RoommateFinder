package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.service.PreferenceService;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.net.asynctasks.PreferenceTaskFacade;
import com.example.roommatefinder.net.asynctasks.UpdatePreferenceTaskFacade;

import java.io.IOException;

public class PreferencePresenter implements PreferenceTaskFacade.Observer, UpdatePreferenceTaskFacade.Observer {

    private final View view;

    @Override
    public void onPreferenceResponseReceived(PreferenceResponse response){
        view.onPreferenceResult(response);
    }

    @Override
    public void onUpdateResponse(CreatePreferenceResponse response) {
        view.onUpdatePreferenceResult(response);
    }

    public interface View {
        void onPreferenceResult(PreferenceResponse response);
        void onUpdatePreferenceResult(CreatePreferenceResponse response);
    }

    public PreferencePresenter(View view) {
        this.view = view;
    }

    public void getPreference(PreferenceRequest request) throws IOException {
        PreferenceService preferenceService = getPreferenceService();
        preferenceService.getPreference(request, this);
    }

    public void updatePreference(CreatePreferenceRequest request) {
        PreferenceService preferenceService = getPreferenceService();
        preferenceService.updatePreference(request, this);
    }


    public PreferenceService getPreferenceService() {
        return new PreferenceService();
    }
}
