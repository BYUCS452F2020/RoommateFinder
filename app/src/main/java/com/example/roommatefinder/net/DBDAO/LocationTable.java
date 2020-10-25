package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Location;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.response.CreateLocationResponse;
import com.example.roommatefinder.model.service.response.LocationResponse;

public class LocationTable {
    public Location Create(CreateLocationRequest request) {
        return new Location("email", "USA", "UT", "Provo", "Land", 123, 12);
    }

    public Boolean Update(CreateLocationRequest request) {
        //might not have to use this
        return true;
    }

    public Boolean Delete(LocationRequest request) {
        return  true;
    }

    public Location Query(LocationRequest request) {
        return new Location("email", "USA", "UT", "Provo", "Land", 123, 12);
    }
}
