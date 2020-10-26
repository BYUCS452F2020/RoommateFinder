package com.example.roommatefinder.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.roommatefinder.R;
import com.example.roommatefinder.model.service.request.LoginRequest;
import com.example.roommatefinder.model.service.response.LoginResponse;
import com.example.roommatefinder.presenter.LoginPresenter;
import com.example.roommatefinder.view.asynctask.LoginTask;
import com.example.roommatefinder.view.main.MainActivity;
import com.facebook.stetho.Stetho;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View, LoginTask.Observer {

    private static final String LOG_TAG = "LoginActivity";

    private LoginPresenter presenter;
    private Toast loginInToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        presenter = new LoginPresenter(this);

        Button loginButton = findViewById(R.id.LoginButton);
        TextView registerLink = findViewById(R.id.link_register);
        loginButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                loginInToast = Toast.makeText(LoginActivity.this, "Logging In", Toast.LENGTH_LONG);
                loginInToast.show();

                EditText loginAlias = findViewById(R.id.input_alias);
                EditText loginPass = findViewById(R.id.input_password);

                // It doesn't matter what values we put here. We will be logged in with a hard-coded dummy user.
                LoginRequest loginRequest = new LoginRequest(loginAlias.getText().toString(), loginPass.getText().toString());
                try {
                    presenter.login(loginRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                LoginTask loginTask = new LoginTask(presenter, LoginActivity.this);
//                loginTask.execute(loginRequest);
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void loginSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(this, MainActivity.class);

        //intent.putExtra(MainActivity.CURRENT_USER_KEY, loginResponse.getUser().toString());
        //intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken().toString());

        loginInToast.cancel();
        startActivity(intent);
    }


    @Override
    public void loginUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(this, "Failed to login. " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(this, "Failed to login because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginResult(LoginResponse response) {
        if (response.getMessage() == null) {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra(MainActivity.CURRENT_USER_KEY, response.getUser().toString());
//            intent.putExtra(MainActivity.AUTH_TOKEN_KEY, response.getAuthToken().toString());
//
//            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Failed to signUp. " + response.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
