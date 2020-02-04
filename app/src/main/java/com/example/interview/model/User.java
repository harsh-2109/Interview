package com.example.interview.model;

public class User {
    private int id;
    private String name;
    private String profilePicture;

    public User(String name, String profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
