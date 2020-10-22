package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;

public class PreferenceTable implements DAOInterface<CreatePreferenceRequest, CreatePreferenceResponse, PreferenceRequest, PreferenceResponse>  {
    @Override
    public CreatePreferenceResponse Create(CreatePreferenceRequest request) {
        return new CreatePreferenceResponse(true);
    }

    @Override
    public CreatePreferenceResponse Update(CreatePreferenceRequest request) {
        return new CreatePreferenceResponse(true);
    }

    @Override
    public PreferenceResponse Delete(PreferenceRequest request) {
        //probably won't need this
        return new PreferenceResponse(new Preference(request.getUsername(), 9, 5, 7, 240.0, "Temp", "2 years"), true);
    }

    @Override
    public PreferenceResponse Query(PreferenceRequest request) {
        return new PreferenceResponse(new Preference(request.getUsername(), 9, 5, 7, 240.0, "Temp", "2 years"), true);
    }
}
