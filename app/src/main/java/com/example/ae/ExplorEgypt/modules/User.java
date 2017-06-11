package com.example.ae.ExplorEgypt.modules;


public class User {
    private String id;
    private String userName;
    private boolean isLoggedIn;
    private String password;
    private String email;

    public User() {
    }

    public User(String id, String userName, boolean isLoggedIn, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.isLoggedIn = isLoggedIn;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
