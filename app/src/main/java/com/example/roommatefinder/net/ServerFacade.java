package com.example.roommatefinder.net;

import com.example.roommatefinder.model.Preference;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.model.service.request.CreateLocationRequest;
import com.example.roommatefinder.model.service.request.CreatePreferenceRequest;
import com.example.roommatefinder.model.service.request.DeleteUserRequest;
import com.example.roommatefinder.model.service.request.LocationRequest;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.LogoutRequest;
import com.example.roommatefinder.model.service.request.PreferenceRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.request.UpdateAuthTokenRequest;
import com.example.roommatefinder.net.asynctasks.CreateLocationTaskFacade;
import com.example.roommatefinder.net.asynctasks.CreatePreferenceTaskFacade;
import com.example.roommatefinder.net.asynctasks.DeleteAuthTokenTaskFacade;
import com.example.roommatefinder.net.asynctasks.DeleteUserTaskFacade;
import com.example.roommatefinder.net.asynctasks.LocationTaskFacade;
import com.example.roommatefinder.net.asynctasks.LoginTaskFacade;
import com.example.roommatefinder.net.asynctasks.PreferenceTaskFacade;
import com.example.roommatefinder.net.asynctasks.RegisterTaskFacade;
import com.example.roommatefinder.net.asynctasks.UpdateAuthTokenTaskFacade;
import com.example.roommatefinder.net.asynctasks.UpdateUserTaskFacade;

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

    public void updateUser(ChangeUserRequest request, UpdateUserTaskFacade.Observer observer) {
        //This has been tested and works, just needs to be called
        //TODO: Implement AuthTokenCheck
        UpdateUserTaskFacade updateUserTask = new UpdateUserTaskFacade(observer);
        updateUserTask.execute(request);
    }

    public void deleteUser(DeleteUserRequest request, DeleteUserTaskFacade.Observer observer) {
        //Tested and working, needs to be called
        //TODO: Implement AuthTokenCheck
        DeleteUserTaskFacade deleteUserTask = new DeleteUserTaskFacade(observer);
        deleteUserTask.execute(request);
    }

    public void updateAuthToken(UpdateAuthTokenRequest request, UpdateAuthTokenTaskFacade.Observer observer) {
        //TODO: Implement AuthTokenCheck
        UpdateAuthTokenTaskFacade updateAuthTokenTask = new UpdateAuthTokenTaskFacade(observer);
        updateAuthTokenTask.execute(request);
    }

    public void deleteAuthToken(LogoutRequest request, DeleteAuthTokenTaskFacade.Observer observer) {
        //TODO: Implement AuthTokenCheck
        DeleteAuthTokenTaskFacade deleteAuthTokenTask = new DeleteAuthTokenTaskFacade(observer);
        deleteAuthTokenTask.execute(request);
    }

    
}
