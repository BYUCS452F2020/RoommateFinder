package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;

public class PreferenceTable {
    public Preference Create(CreatePreferenceRequest request) {
        return new Preference(request.getPreference().getUsername(), 9, 5, 7, 240.0, "Temp", "2 years");
    }

    public Boolean Update(CreatePreferenceRequest request) {
        return true;
    }

    public Boolean Delete(PreferenceRequest request) {
        //probably won't need this
        return true;
    }

    public Preference Query(PreferenceRequest request) {
        return new Preference(request.getUsername(), 9, 5, 7, 240.0, "Temp", "2 years");
    }
}
