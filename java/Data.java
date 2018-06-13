package com.example.paramedics.myrxjava.NodeJS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("result")
    @Expose
    private List<User> users = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Data{" +
                "users=" + users +
                ", message=" + message +
                '}';
    }
}
