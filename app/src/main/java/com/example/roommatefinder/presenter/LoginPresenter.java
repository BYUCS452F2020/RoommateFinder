package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.LoginService;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.net.asynctasks.LoginTaskFacade;

import java.io.IOException;

public class LoginPresenter implements LoginTaskFacade.Observer {

    private final View view;

    @Override
    public void onLoginResponseReceived(LoginResponse response) {
        view.onLoginResult(response);
    }


    public interface View {
        public void onLoginResult(LoginResponse response);
        // If needed, specify methods here that will be called on the view in response to model updates
    }


    public LoginPresenter(View view) {
        this.view = view;
    }


    public void login(LoginRequest loginRequest) throws IOException {
        LoginService loginService = getLoginService();
        loginService.login(loginRequest, this);
    }

    public LoginService getLoginService(){
        return new LoginService();
    }
}
