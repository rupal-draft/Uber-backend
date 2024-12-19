package com.project.uber.Uber.dto;

import com.project.uber.Uber.entities.enums.Roles;

import java.util.Set;

public class UserDto {

    private String name;
    private String email;
    private Set<Roles> roles;

    public UserDto() {
    }

    public UserDto(String name, String email, Set<Roles> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
