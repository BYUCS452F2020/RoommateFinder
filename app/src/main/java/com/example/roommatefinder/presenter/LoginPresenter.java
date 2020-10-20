package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.LoginService;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;

import java.io.IOException;

public class LoginPresenter {

    private final View view;


    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }


    public LoginPresenter(View view) {
        this.view = view;
    }


    public LoginResponse login(LoginRequest loginRequest) throws IOException {
        LoginService loginService = getLoginService();
        return loginService.login(loginRequest);
    }

    public LoginService getLoginService(){
        return new LoginService();
    }
}
