package com.blog_application.app.BlogApplication.controllers;

import com.blog_application.app.BlogApplication.dto.UserDTO;
import com.blog_application.app.BlogApplication.entities.User;
import com.blog_application.app.BlogApplication.exceptions.InvalidCredentialsException;
import com.blog_application.app.BlogApplication.services.UserService;
import com.blog_application.app.BlogApplication.utlis.ApiResponse;
import jakarta.validation.Valid;
import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.decimal.DecimalMinValidatorForLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")

public class UserController {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        UserDTO createdUser = userService.registerUser(userDTO);
        return buildResponse(HttpStatus.CREATED, "User registered successfully!", createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody User user){
        try {
            String token = userService.verify(user);
            return buildResponse(HttpStatus.OK, "Logged in successfully!", token);
        } catch (UsernameNotFoundException e) {
            return buildResponse(HttpStatus.NOT_FOUND, "Username not found!", null);
        } catch (Exception e) {
            return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!", null);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId){
        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        return buildResponse(HttpStatus.OK, String.format("User with ID: %d updated successfully!", userId), updatedUser);

    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers(){
        List<UserDTO> allUsers = userService.getAllUsers();
        return buildResponse(HttpStatus.OK, "User details fetched successfully", allUsers);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable Integer userId){
        UserDTO getUserByIdDetail = userService.getUserById(userId);
        return buildResponse(HttpStatus.OK, String.format("User with ID: %d fetched successfully!", userId), getUserByIdDetail);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Integer userId){
       userService.deleteUser(userId);
        return buildResponse(HttpStatus.OK, "User deleted successfully", null);
    }

    private <T> ResponseEntity<ApiResponse<T>> buildResponse(HttpStatus status, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(message, data);
        return new ResponseEntity<>(response, status);
    }

}
