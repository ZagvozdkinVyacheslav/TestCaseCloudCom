



package com.example.task.entyties;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String username;

    private String password;
    private boolean enabled;

    public User(long id, String username, String password, boolean enabled) {
        this.id = id;
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        this.password = encoder.encode(password);
        this.enabled = enabled;
    }
    public User(String username, String password, boolean enabled) {
        this.username = username;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        this.password = encoder.encode(password);
        this.enabled = enabled;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }





    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
        this.password = encoder.encode(password);
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setAuthority(boolean enabled) {
        this.enabled = enabled;
    }


}
