package com.example.roommatefinder.model.service.request;

import com.example.roommatefinder.model.Location;

public class CreateLocationRequest extends Request {
    private Location location;

    public CreateLocationRequest(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
