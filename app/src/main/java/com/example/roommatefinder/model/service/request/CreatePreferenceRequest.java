package com.example.roommatefinder.model.service.request;

import com.example.roommatefinder.model.Preference;

public class CreatePreferenceRequest {
    private Preference preference;

    public CreatePreferenceRequest(Preference preference) {
        this.preference = preference;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }
}
