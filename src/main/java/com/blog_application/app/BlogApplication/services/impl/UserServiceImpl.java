package com.blog_application.app.BlogApplication.services.impl;

import com.blog_application.app.BlogApplication.entities.User;
import com.blog_application.app.BlogApplication.exceptions.ResourceNotFoundException;
import com.blog_application.app.BlogApplication.dto.UserDTO;
import com.blog_application.app.BlogApplication.repositories.UserRepository;
import com.blog_application.app.BlogApplication.services.JWTService;
import com.blog_application.app.BlogApplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        // Save and return the new user as DTO
        User user = userRepository.save(mapToEntity(userDTO));
        return mapToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        // Fetch existing user or throw an exception
        User userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // Update user details
        updateUserDetails(userDetails, userDTO);

        // Save updated user and return as DTO
        User updatedUser = userRepository.save(userDetails);
        return mapToDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        // Fetch user by ID or throw exception
        User userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        // Return as DTO
        return mapToDTO(userDetails);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // Fetch all users and convert to DTO list
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        // Fetch user or throw exception, then delete
        User userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(userDetails);
    }

    @Override
    public String verify(User user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        return "Failed";
    }

    // Helper method to map UserDTO to User entity
    private User mapToEntity(UserDTO userDTO) {
        // Using Model Mapper
        return modelMapper.map(userDTO, User.class);
//        User user = new User();
//        user.setFullName(userDTO.getFullName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setGender(userDTO.getGender());
//        user.setMobileNumber(userDTO.getMobileNumber());
//        return  user


    }

    // Helper method to map User entity to UserDTO
    private UserDTO mapToDTO(User user) {
        // Using Model Mapper
        return modelMapper.map(user, UserDTO.class);

//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setFullName(user.getFullName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setGender(user.getGender());
//        userDTO.setMobileNumber(user.getMobileNumber());
//        return userDTO;
    }

    // Helper method to update existing user details
    private void updateUserDetails(User userDetails, UserDTO userDTO) {
        userDetails.setFullName(userDTO.getFullName());
        userDetails.setMobileNumber(userDTO.getMobileNumber());
        userDetails.setEmail(userDTO.getEmail());
        userDetails.setGender(userDTO.getGender());
        userDetails.setPassword(userDTO.getPassword());
    }
}
