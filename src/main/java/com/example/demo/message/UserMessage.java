package com.example.demo.message;

import com.example.demo.model.User;

public class UserMessage {
    private String message;
    private User data;

    public String getMessage() {
        return message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
