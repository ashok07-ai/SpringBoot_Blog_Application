package com.blog_application.app.BlogApplication.controllers;

import com.blog_application.app.BlogApplication.dto.UserDTO;
import com.blog_application.app.BlogApplication.services.UserService;
import com.blog_application.app.BlogApplication.utlis.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.registerUser(userDTO);
        return buildResponse(HttpStatus.CREATED, "User registered successfully!", createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId){
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
