package com.example.demo.message;

import com.example.demo.model.Transaction;

import java.util.List;

public class TransactionsMessage {
    private String message;
    private List<Transaction> data;

    public String getMessage() {
        return message;
    }

    public List<Transaction> getData() {
        return data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<Transaction> data) {
        this.data = data;
    }
}
