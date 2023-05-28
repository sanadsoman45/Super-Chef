package com.superchef.Super.Chef.entities;

import java.time.LocalDateTime;


public class ExceptionResponse {

    //define the fields

    private int statusCode;

    private String message;

    private LocalDateTime timeStamp;

    //getters and setters

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    //define toString

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
