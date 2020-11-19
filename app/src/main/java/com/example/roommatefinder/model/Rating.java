package com.example.roommatefinder.model;

import java.io.Serializable;

public class Rating implements Serializable {
    private String ratingID;
    private String email;
    private String ratingGiver;
    private int score;
    private String comment;

    public Rating(String ratingID, String email, String ratingGiver, int score, String comment) {
        this.ratingID = ratingID;
        this.email = email;
        this.ratingGiver = ratingGiver;
        this.score = score;
        this.comment = comment;
    }

    public String getRatingID() {
        return ratingID;
    }

    public void setRatingID(String ratingID) {
        this.ratingID = ratingID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
                this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRatingGiver(String ratingGiver) {
        this.ratingGiver = ratingGiver;
    }

    public String getRatingGiver() {
        return ratingGiver;
    }
}
