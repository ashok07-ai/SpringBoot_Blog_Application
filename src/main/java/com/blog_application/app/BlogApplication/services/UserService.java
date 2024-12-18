package com.blog_application.app.BlogApplication.services;

import com.blog_application.app.BlogApplication.dto.UserDTO;
import com.blog_application.app.BlogApplication.entities.User;

import java.util.List;

public interface UserService {

    /**
     * Registers a new user in the system.
     * @param user The user data transfer object (DTO) containing the user's details.
     * @return The registered user information after successful creation.
     */
    UserDTO registerUser(UserDTO user);

    /**
     * Updates an existing user's information.
     * @param user The updated user DTO with new information.
     * @return The updated user data after successful update.
     */
    UserDTO updateUser(UserDTO user, Integer userId);

    /**
     * Retrieves the details of a user based on the provided user ID.
     * @param userId The unique identifier of the user to fetch.
     * @return The user DTO containing the user's information.
     */
    UserDTO getUserById(Integer userId);

    /**
     * Retrieves a list of all users present in the system.
     * @return A list of UserDTOs representing all registered users.
     */
    List<UserDTO> getAllUsers();

    /**
     * Deletes a user from the system based on their ID.
     * @param userId The unique identifier of the user to be deleted.
     */
    void deleteUser(Integer userId);

    /**
     * Verifies the given user's credentials and generates a JWT upon successful authentication.
     *
     * @param user the User object containing authentication details for verification.
     * @return a JWT as a String if the user is successfully authenticated; otherwise, returns an error message.
     */
    String verify(User user);
}
