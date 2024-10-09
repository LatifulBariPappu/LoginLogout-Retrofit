package com.example.restapis.models;

public class signinmodel {
    String  message;

    public signinmodel() {
    }

    public signinmodel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
