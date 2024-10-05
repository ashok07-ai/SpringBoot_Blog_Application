package com.blog_application.app.BlogApplication.entities;

import com.blog_application.app.BlogApplication.enums.Gender;
import jakarta.persistence.*;

@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private  String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String mobileNumber;

    // Getters and Setters
    public int getId(){
        return id;
    }

    public void setId(int _id){
        this.id = _id;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String _fullName){
        this.fullName = _fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String _email) {
        this.email = _email;
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
