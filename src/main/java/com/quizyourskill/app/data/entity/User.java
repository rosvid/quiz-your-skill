package com.quizyourskill.app.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quizyourskill.app.data.Role;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class User extends AbstractEntity {
    @Nonnull
    private String username;

    @Nonnull
    private String name;

    @JsonIgnore
    private String hashedPassword;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Nonnull
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}