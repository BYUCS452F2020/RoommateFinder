package com.example.roommatefinder.net;

import com.example.roommatefinder.model.User;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.net.asynctasks.LoginTaskFacade;
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

    public void post() {

    }
    
}
