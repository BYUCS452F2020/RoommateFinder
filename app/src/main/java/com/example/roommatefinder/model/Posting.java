package com.example.roommatefinder.model;

import java.io.Serializable;

public class Posting implements Serializable {
    private String postID;
    private String email;
    private String postContent;
    private int vacancyNumber;

    public Posting(String postID, String email, String postContent, int vacancyNumber){
        this.postID = postID;
        this.email = email;
        this.postContent = postContent;
        this.vacancyNumber = vacancyNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getVacancyNumber() {
        return vacancyNumber;
    }

    public void setVacancyNumber(int vacancyNumber) {
        this.vacancyNumber = vacancyNumber;
    }
}

