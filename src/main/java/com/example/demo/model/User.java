package com.example.demo.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String username;
    private String email;

    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

}