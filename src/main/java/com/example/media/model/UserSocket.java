package com.example.media.model;

public class UserSocket {
    private String name;
    private String message;
    private String userNameFriend;

    public UserSocket() {
    }

    public UserSocket(String name, String message, String userNameFriend) {
        this.name = name;
        this.message = message;
        this.userNameFriend = userNameFriend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserNameFriend() {
        return userNameFriend;
    }

    public void setUserNameFriend(String userNameFriend) {
        this.userNameFriend = userNameFriend;
    }
}
