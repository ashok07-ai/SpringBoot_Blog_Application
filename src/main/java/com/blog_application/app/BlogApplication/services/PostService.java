package com.blog_application.app.BlogApplication.services;

import com.blog_application.app.BlogApplication.dto.PostDTO;
import com.blog_application.app.BlogApplication.entities.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

    /**
     * Registers a new post in the system.
     * @param post The post data transfer object (DTO) containing the post's details.
     * @return The registered post information after successful creation.
     */
     PostDTO createPost(PostDTO post, Integer categoryId, Integer postId);

    /**
     * Updates an existing post's information.
     * @param post The updated post DTO with new information.
     * @param postId The unique identifier of the post to update.
     * @return The updated post data after successful update.
     */
     PostDTO updatePost(PostDTO post, Integer postId);

    /**
     * Retrieves the details of a post based on the provided post ID.
     * @param postId The unique identifier of the post to fetch.
     * @return The post DTO containing the post's information.
     */
     PostDTO getPostById(Integer postId);

    /**
     * Retrieves a list of all posts present in the system.
     * @return A list of PostDTOs representing all registered posts.
     */
     List<PostDTO> getAllPosts();

    /**
     * Deletes a post from the system based on its ID.
     * @param postId The unique identifier of the post to be deleted.
     */
     void deletePost(Integer postId);

    /**
     * Retrieves a list of posts associated with a specific category.
     * @param categoryId The unique identifier of the category.
     * @return A list of posts belonging to the specified category.
     */
     List<Post> getPostByCategory(Integer categoryId);

    /**
     * Retrieves a list of posts associated with a specific user.
     * @param userId The unique identifier of the user.
     * @return A list of posts belonging to the specified user.
     */
     List<Post> getPostByUser(Integer userId);
}
