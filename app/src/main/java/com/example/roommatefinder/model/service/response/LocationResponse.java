package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Location;

public class LocationResponse {
    private Location location;

    public LocationResponse(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
