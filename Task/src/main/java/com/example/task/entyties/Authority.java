package com.example.task.entyties;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String username;
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Role Role;

    public Authority(long id, String username, Role Role) {
        this.id = id;
        this.username = username;
        this.Role = Role;
    }
    public Authority(String username, Role Role) {
        this.id = id;
        this.username = username;
        this.Role = Role;
    }

    public Authority() {

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

    public Role getRole() {
        return Role;
    }

    public void setRole(Role Role) {
        this.Role = Role;
    }
}
