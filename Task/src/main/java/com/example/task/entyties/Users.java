package com.example.task.entyties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String login;
    private String password;

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public Users() {

    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
