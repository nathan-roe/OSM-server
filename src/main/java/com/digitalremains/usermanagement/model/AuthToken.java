package com.digitalremains.usermanagement.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne
    @JoinColumn(name = "id")
    private ManagingUser user;

    public AuthToken() {}

    public AuthToken(final ManagingUser user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public ManagingUser getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(ManagingUser user) {
        this.user = user;
    }
}
