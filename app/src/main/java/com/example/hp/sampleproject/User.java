package com.example.hp.sampleproject;

/**
 * Created by HP on 11/28/2017.
 */

public class User {
    private String name;
    private String email;
    private String password;
    private Integer api_token;

    public User(){

    }
    public User(String name, String email, String password,Integer api_token) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.api_token = api_token;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getApi_token() {
        return api_token;
    }
}
