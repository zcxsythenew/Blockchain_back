package com.example.demo.message;

import com.example.demo.model.User;

public class DatabaseMessage {
    public String message;
    public User user;
    public String contractAddress;

    public String getMessage() {
        return message;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public User getUser() {
        return user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
