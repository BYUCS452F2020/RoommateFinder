package com.example.roommatefinder.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.roommatefinder.ProfileActivity;
import com.example.roommatefinder.R;
import com.example.roommatefinder.model.AuthToken;
import com.example.roommatefinder.model.SessionCache;
import com.example.roommatefinder.model.User;
import com.example.roommatefinder.view.ChoosePreferenceActivity;
import com.example.roommatefinder.view.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    public static final String CURRENT_USER_KEY = "CurrentUser";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    private ImageView profilePic;
    private ImageView preferencesPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profilePic = findViewById(R.id.profile_pic);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        preferencesPic = findViewById(R.id.preferences_pic);
        preferencesPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, ChoosePreferenceActivity.class);
                    intent.putExtra("mode", 1);
                    startActivity(intent);
                }finally {
                    finish();
                }
            }
        });
        //User user = (User) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        User user = SessionCache.getInstance().getUser();
        //if(user == null) {
           //throw new RuntimeException("User not passed to activity");
        //}

        //AuthToken authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);
        AuthToken authToken = SessionCache.getInstance().getAuthToken();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), user, authToken);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);

        // We should use a Java 8 lambda function for the listener (and all other listeners), but
        // they would be unfamiliar to many students who use this code.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Ability to add post from here", Toast.LENGTH_LONG).show();

            }
        });

        TextView firstName = findViewById(R.id.userName);
        firstName.setText(SessionCache.getInstance().getUser().getFirstName());

    }


}
