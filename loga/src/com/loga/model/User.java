package com.loga.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name = "username",unique = true,length = 50)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "active")
    private boolean active;

    @OneToOne(mappedBy = "user")
    private Profile profile;

    public static Role[] getRoles() {
        return Role.values();
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean getActive() {
        return this.active;
    }
    
    public void setActive(boolean stat) {
        this.active = stat;
    }

    public boolean isActive() {
        return active;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        profile.setUser(this);
        this.profile = profile;
    }

    public enum Role {
        ADMINISTRATEUR,
        SUPERVISEUR,
        UTILISATEUR
    }
}
