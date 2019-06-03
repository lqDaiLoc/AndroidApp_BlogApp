package com.example.blogappdemo;

import java.util.Date;

public class Comment {


    private String message, user_id;
    private Date timestamp;

    public String getMessage() {
        return message;
    }

    public Comment() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
