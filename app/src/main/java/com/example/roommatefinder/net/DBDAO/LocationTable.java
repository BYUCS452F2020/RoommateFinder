package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.Location;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.response.CreateLocationResponse;
import com.example.roommatefinder.model.service.response.LocationResponse;

public class LocationTable implements DAOInterface<CreateLocationRequest, CreateLocationResponse, LocationRequest, LocationResponse> {
    @Override
    public CreateLocationResponse Create(CreateLocationRequest request) {
        return new CreateLocationResponse(true);
    }

    @Override
    public CreateLocationResponse Update(CreateLocationRequest request) {
        //might not have to use this
        return new CreateLocationResponse(true);
    }

    @Override
    public LocationResponse Delete(LocationRequest request) {
        return new LocationResponse(new Location(request.getUsername(), "USA", "UT", "Provo", "Land", 123, 12));
    }

    @Override
    public LocationResponse Query(LocationRequest request) {
        return new LocationResponse(new Location(request.getUsername(), "USA", "UT", "Provo", "Land", 123, 12));
    }
}
