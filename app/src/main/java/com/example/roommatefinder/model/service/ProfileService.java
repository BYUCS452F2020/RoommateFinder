package com.example.roommatefinder.model.service;

import com.example.roommatefinder.model.service.request.ChangeUserRequest;
import com.example.roommatefinder.net.ServerFacade;
import com.example.roommatefinder.net.asynctasks.ChangeUserInfoTaskFacade;

public class ProfileService {

    public void changeProfileInfo(ChangeUserRequest request, ChangeUserInfoTaskFacade.Observer observer) {
        getServerFacade().changeUserInfo(request, observer);
    }

    private ServerFacade getServerFacade() {
        return new ServerFacade();
    }

}
