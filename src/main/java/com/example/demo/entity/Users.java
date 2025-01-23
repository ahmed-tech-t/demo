package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;


@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;

    public Users() {
    }

    public Users(String userName, String password) {
        this.username = userName;
        this.password = password;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Users{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
