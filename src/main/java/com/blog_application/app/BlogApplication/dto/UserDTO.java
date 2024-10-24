package com.blog_application.app.BlogApplication.dto;

import com.blog_application.app.BlogApplication.enums.Gender;
import jakarta.validation.constraints.*;

public class UserDTO {
    @NotBlank(message = "Full name is required")
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private  String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must contain at least one uppercase letter, one number, and one special character")
    private String password;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNumber;


    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String _email) {
        this.email = _email;
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
