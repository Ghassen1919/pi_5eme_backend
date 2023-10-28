package com.youtube.jwt.entity;

public class JwtRequest {

    private String userName;
    private String email;
    private String userPassword;

    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setEmail(String userName) {
        this.email = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
