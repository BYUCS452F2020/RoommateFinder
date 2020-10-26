package com.example.roommatefinder.model.service.response;

import com.example.roommatefinder.model.Location;

public class LocationResponse extends Response {
    private Location location;

    public LocationResponse(String message) {
        super(false, message);
    }

    public LocationResponse(Location location) {
        super(true, null);
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
