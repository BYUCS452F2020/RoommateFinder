package com.example.roommatefinder.net;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.net.asynctasks.CreateLocationTaskFacade;
import com.example.roommatefinder.net.asynctasks.CreatePreferenceTaskFacade;
import com.example.roommatefinder.net.asynctasks.LocationTaskFacade;
import com.example.roommatefinder.net.asynctasks.LoginTaskFacade;
import com.example.roommatefinder.net.asynctasks.PreferenceTaskFacade;
import com.example.roommatefinder.net.asynctasks.RegisterTaskFacade;

public class ServerFacade {

    public void login(LoginRequest request, LoginTaskFacade.Observer observer) {
        LoginTaskFacade loginTask = new LoginTaskFacade(observer);
        loginTask.execute(request);
    }

    public void register(RegisterRequest request, RegisterTaskFacade.Observer observer){
        RegisterTaskFacade registerTask = new RegisterTaskFacade(observer);
        registerTask.execute(request);
    }

    public void getPreference(PreferenceRequest request, PreferenceTaskFacade.Observer observer) {
        PreferenceTaskFacade preferenceTaskFacade = new PreferenceTaskFacade(observer);
        preferenceTaskFacade.execute(request);
    }

    public void addPreference(CreatePreferenceRequest request, CreatePreferenceTaskFacade.Observer observer) {
        CreatePreferenceTaskFacade createPreferenceTask = new CreatePreferenceTaskFacade(observer);
        createPreferenceTask.execute(request);
    }

    public void getLocation(LocationRequest request, LocationTaskFacade.Observer observer) {
        LocationTaskFacade locationTask = new LocationTaskFacade(observer);
        locationTask.execute(request);
    }

    public void createLocation(CreateLocationRequest request, CreateLocationTaskFacade.Observer observer) {
        CreateLocationTaskFacade createLocationTask = new CreateLocationTaskFacade(observer);
        createLocationTask.execute(request);
    }

    public void updateUser(ChangeUserRequest request) {

    }
    
}
