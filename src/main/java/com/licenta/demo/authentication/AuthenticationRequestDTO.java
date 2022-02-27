package com.licenta.demo.authentication;

public class AuthenticationRequestDTO {
    private String username;
    private String password;

    public AuthenticationRequestDTO() {}

    public AuthenticationRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequestDTO [username=" + username + ", password=" + password + "]";
    }
}