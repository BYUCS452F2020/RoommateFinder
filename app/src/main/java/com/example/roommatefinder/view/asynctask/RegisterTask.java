package com.example.roommatefinder.view.asynctask;

import android.os.AsyncTask;

import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.presenter.RegisterPresenter;

import java.io.IOException;

public class RegisterTask extends AsyncTask<RegisterRequest, Void, RegisterResponse> {

    private final RegisterPresenter presenter;
    private final Observer observer;
    private Exception exception;

    public interface Observer{
        void registerSuccessful(RegisterResponse registerResponse);
        void registerUnsuccessful(RegisterResponse registerResponse);
        void handleException(Exception ex);
    }

    public RegisterTask(RegisterPresenter presenter, Observer observer){
        if(observer == null){
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }
    @Override
    protected RegisterResponse doInBackground(RegisterRequest... registerRequests) {
        RegisterResponse registerResponse = null;

        try{
            registerResponse = presenter.register(registerRequests[0]);

        } catch (IOException e){
            exception = e;
        }

        return registerResponse;
    }

    @Override
    protected void onPostExecute(RegisterResponse registerResponse) {
        if(exception != null){
            observer.handleException(exception);
        }
        else if(registerResponse.isSuccess()){
            observer.registerSuccessful(registerResponse);
        }
        else{
            observer.registerUnsuccessful(registerResponse);
        }
    }
}
