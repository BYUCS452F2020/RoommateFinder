package com.example.roommatefinder.net.DBDAO;

import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.response.CreateLocationResponse;
import com.example.roommatefinder.model.service.response.LocationResponse;

public class LocationTable implements DAOInterface<CreateLocationRequest, CreateLocationResponse, LocationRequest, LocationResponse> {
    @Override
    public CreateLocationResponse Create(CreateLocationRequest request) {
        return null;
    }

    @Override
    public CreateLocationResponse Update(CreateLocationRequest request) {
        //might not have to use this
        return null;
    }

    @Override
    public LocationResponse Delete(LocationRequest request) {
        return null;
    }

    @Override
    public LocationResponse Query(LocationRequest request) {
        return null;
    }
}
