package com.example.roommatefinder.model;

public class Rating {
    private String ratingID;
    private String username;
    private String ratingGiver;
    private int score;
    private String comment;

    public Rating(String ratingID, String username, String ratingGiver, int score, String comment) {
        this.ratingID = ratingID;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
