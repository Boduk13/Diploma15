package com.example.bodik13.duplom15;

/**
 * Created by Bodik13 on 10.04.2015.
 */
public class User {

    private String user_id;
    private String user_name;
    private String user_pass;

    public User(String user_id, String user_name, String user_pass) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_pass = user_pass;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }
}
