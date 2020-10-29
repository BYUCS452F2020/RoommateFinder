package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Preference;

public class PreferenceResponse extends Response {
    private Preference preference;

    public PreferenceResponse(Preference preference, boolean success) {
        super(success);
        this.preference = preference;
    }

    public PreferenceResponse(boolean success, String message) {
        super(success, message);
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }
}
