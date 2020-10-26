package com.example.roommatefinder.model;

public class SessionCache {
    private static SessionCache instance;
    private static User user;
    private static AuthToken authToken;
    public static SessionCache getInstance() {
        if (instance == null) {
            instance = new SessionCache();
        }
        return instance;
    }

    public void setUser(User userTemp) {
        user = userTemp;
    }

    public User getUser() {
        return user;
    }

    public void setAuthToken(AuthToken authTokenTemp) {
        authToken = authTokenTemp;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

}
