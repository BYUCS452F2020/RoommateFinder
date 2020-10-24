package com.example.roommatefinder.presenter;

import com.example.roommatefinder.model.service.RegisterService;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.net.asynctasks.RegisterTaskFacade;

import java.io.IOException;

public class RegisterPresenter implements RegisterTaskFacade.Observer {

    private final View view;

    @Override
    public void onRegisterResponseReceived(RegisterResponse response) {
        view.onRegisterResult(response);
    }

    public interface View {
        void onRegisterResult(RegisterResponse registerResponse);
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public RegisterPresenter(View view){
        this.view = view;
    }

    public void register(RegisterRequest request) throws IOException {
        RegisterService registerService = getRegisterService();
        registerService.register(request, this);
    }

    public RegisterService getRegisterService(){return new RegisterService();}
}
