package com.blog_application.app.BlogApplication.dto;

import com.blog_application.app.BlogApplication.enums.Gender;

public class UserDTO {
    private int id;
    private String fullName;
    private  String email;
    private String password;
    private Gender gender;
    private String mobileNumber;


    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String _email) {
        this.email = _email;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String _fullName) {
        this.fullName = _fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender _gender) {
        this.gender = _gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String _mobileNumber) {
        this.mobileNumber = _mobileNumber;
    }



}
