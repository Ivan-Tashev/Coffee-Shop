package com.example.coffeeshop.model.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginBindModel {
    @NotNull
    @Size(min = 5, max = 20)
    private String username;
    @NotNull
    @Size(min = 3)
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginBindModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
