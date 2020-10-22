package com.example.roommatefinder.model.service.response;

class Response {

    private boolean success;
    private String message;


    Response(boolean success) {
        this(success, null);
    }


    Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }


    public String getMessage() {
        return message;
    }
}