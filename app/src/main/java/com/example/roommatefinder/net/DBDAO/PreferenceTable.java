package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;

public class PreferenceTable implements DAOInterface<CreatePreferenceRequest, CreatePreferenceResponse, PreferenceRequest, PreferenceResponse>  {
    @Override
    public CreatePreferenceResponse Create(CreatePreferenceRequest request) {
        return null;
    }

    @Override
    public CreatePreferenceResponse Update(CreatePreferenceRequest request) {
        return null;
    }

    @Override
    public PreferenceResponse Delete(PreferenceRequest request) {
        //probably won't need this
        return null;
    }

    @Override
    public PreferenceResponse Query(PreferenceRequest request) {
        return null;
    }
}
