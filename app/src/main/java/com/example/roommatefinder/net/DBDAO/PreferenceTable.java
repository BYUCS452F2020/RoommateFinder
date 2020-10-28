package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.response.CreatePreferenceResponse;
import com.example.roommatefinder.model.service.response.PreferenceResponse;
import com.example.roommatefinder.net.SQLAccess;

import java.sql.SQLException;

public class PreferenceTable {
    public CreatePreferenceResponse Create(CreatePreferenceRequest request) {
        try {
            return SQLAccess.createPreference(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public CreatePreferenceResponse Update(CreatePreferenceRequest request) {
        try {
           return SQLAccess.createPreference(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public PreferenceResponse Query(PreferenceRequest request) {
        try {
            return SQLAccess.queryPreference(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
