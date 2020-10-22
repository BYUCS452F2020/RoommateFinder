package com.example.roommatefinder.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.roommatefinder.R;
import com.example.roommatefinder.model.service.request.RegisterRequest;
import com.example.roommatefinder.model.service.response.RegisterResponse;
import com.example.roommatefinder.presenter.RegisterPresenter;
import com.example.roommatefinder.view.asynctask.RegisterTask;
import com.example.roommatefinder.view.main.MainActivity;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity implements RegisterPresenter.View, RegisterTask.Observer {

    private static final String LOG_TAG = "RegisterActivity";

    private RegisterPresenter presenter;
    private Toast registerToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenter(this);

        Button registerButton = findViewById(R.id.RegisterButton);
        TextView loginLink = findViewById(R.id.link_login);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerToast = Toast.makeText(RegisterActivity.this, "Logging In", Toast.LENGTH_LONG);
                registerToast.show();

                EditText firstName = findViewById(R.id.input_first_name);
                EditText lastName = findViewById(R.id.input_last_name);
                EditText gender = findViewById(R.id.input_gender);
                EditText age = findViewById(R.id.input_age);
                EditText email = findViewById(R.id.input_email);
                EditText password = findViewById(R.id.input_password);
                EditText phoneNumber = findViewById(R.id.input_phone_number);

                RegisterRequest registerRequest = new RegisterRequest(firstName.getText().toString(), lastName.getText().toString(),
                        gender.getText().charAt(0), Integer.parseInt(age.getText().toString()), email.getText().toString(), password.getText().toString(),
                        phoneNumber.getText().toString());
                try {
                    presenter.register(registerRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                RegisterTask registerTask = new RegisterTask(presenter, RegisterActivity.this);
//                registerTask.execute(registerRequest);
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void registerSuccessful(RegisterResponse registerResponse) {
        Intent intent = new Intent(this, MainActivity.class);

        //intent.putExtra(MainActivity.CURRENT_USER_KEY, registerResponse.getUser());
        //intent.putExtra(MainActivity.AUTH_TOKEN_KEY, registerResponse.getAuthToken());

        registerToast.cancel();
        startActivity(intent);
    }

    @Override
    public void registerUnsuccessful(RegisterResponse registerResponse) {
        Toast.makeText(this, "Failed to register " + registerResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleException(Exception ex) {
        Log.e(LOG_TAG, ex.getMessage(), ex);
        Toast.makeText(this, "Failed to login because of exception: " + ex.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRegisterResult(RegisterResponse registerResponse) {
        //handle response
    }
}
