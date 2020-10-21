package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Preference;

public class PreferenceResponse extends Response{
    private Preference preference;

    PreferenceResponse(Preference preference, boolean success) {
        super(success);
        this.preference = preference;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }
}
