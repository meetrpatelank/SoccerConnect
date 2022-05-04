package com.soccerconnect.models.user;

public class UserModel {

    // Model class for a user. The user can be player team or admin. Player and team models will extend this class

    String userId;
    String role;
    String email;
    String name;
    String mobile;
    String password;
    String category;

    public UserModel(String userId, String role, String email, String name, String mobile, String password, String category) {
        this.userId = userId;
        this.role = role;
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.category = category;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
