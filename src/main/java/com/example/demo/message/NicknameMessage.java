package com.example.demo.message;

import com.example.demo.model.Nickname;

import java.util.List;

public class NicknameMessage {
    public String message;
    public List<Nickname> data;

    public String getMessage() {
        return message;
    }

    public List<Nickname> getData() {
        return data;
    }

    public void setData(List<Nickname> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
