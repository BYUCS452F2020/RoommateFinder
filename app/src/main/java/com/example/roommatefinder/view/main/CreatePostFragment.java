package com.example.roommatefinder.view.main;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roommatefinder.R;
import com.example.roommatefinder.model.Posting;
import com.example.roommatefinder.model.service.request.CreatePostingRequest;
import com.example.roommatefinder.model.service.response.CreatePostingResponse;
import com.example.roommatefinder.presenter.CreatePostingPresenter;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreatePostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePostFragment extends Fragment implements CreatePostingPresenter.View {

    private static final String LOG_TAG = "CreatePostActivity";

    private static final String ARG_USER_EMAIL = "UserEmail";

    private String userEmail;
    private EditText postContent;
    private EditText vacancy;
    private Button submitButton;

    private CreatePostingPresenter presenter;

    public CreatePostFragment() {
        // Required empty public constructor
    }

    public static CreatePostFragment newInstance(String userEmail) {
        CreatePostFragment fragment = new CreatePostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_EMAIL, userEmail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userEmail = getArguments().getString(ARG_USER_EMAIL);
        }

        presenter = new CreatePostingPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        postContent = (EditText) view.findViewById(R.id.post_content);
        vacancy = (EditText) view.findViewById(R.id.vacancy_count);
        submitButton = (Button) view.findViewById(R.id.submit_post_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast failureToast;
                if (postFieldsFilled()){

                    String content = postContent.getText().toString();
                    Integer vacancyNum = Integer.parseInt(postContent.getText().toString());

                    CreatePostingRequest request = new CreatePostingRequest(new Posting("-", userEmail, content, vacancyNum));

                    try {
                        presenter.createPosting(request);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    failureToast = Toast.makeText(getActivity(), "Please fill all of the fields before submitting.", Toast.LENGTH_LONG );
                    failureToast.show();
                }
            }
        });

        return view;
    }

    private Boolean postFieldsFilled() {
        if (!postContent.getText().toString().equals("")) {
            return false;
        }
        if (!vacancy.getText().toString().equals("")) {
            return false;
        }

        return true;
    }

    @Override
    public void onCreatePostingResult(CreatePostingResponse response) {
        if (response.isSuccess()) {
            //Not sure what we want to do here. Do we need to add the post to the postsRecylcerView;
        }
        else {
            Toast failureToast = Toast.makeText(getActivity(), "Please fill all of the fields before submitting.", Toast.LENGTH_LONG );
            failureToast.show();
        }
    }
}