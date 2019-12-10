package com.example.demo.model;

public class User {
    public String address;
    public String privateKey;
    public String publicKey;
    public boolean admin;

    public String getAddress() {
        return address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
