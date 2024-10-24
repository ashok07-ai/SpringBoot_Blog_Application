package com.blog_application.app.BlogApplication.services.impl;

import com.blog_application.app.BlogApplication.dto.CategoryDTO;
import com.blog_application.app.BlogApplication.dto.PostDTO;
import com.blog_application.app.BlogApplication.entities.Category;
import com.blog_application.app.BlogApplication.entities.Post;
import com.blog_application.app.BlogApplication.entities.User;
import com.blog_application.app.BlogApplication.exceptions.ResourceNotFoundException;
import com.blog_application.app.BlogApplication.repositories.CategoryRepository;
import com.blog_application.app.BlogApplication.repositories.PostRepository;
import com.blog_application.app.BlogApplication.repositories.UserRepository;
import com.blog_application.app.BlogApplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        // Fetch user and category details
        User userDetails = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Category categoryDetails = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        // Convert DTO to entity
        Post post = modelMapper.map(postDTO, Post.class);
        post.setPostImage("default.png"); // Default image
        post.setCreatedDate(new Date()); // Set current date
        post.setUser(userDetails); // Associate user
        post.setCategory(categoryDetails); // Associate category

        // Save the post and return the DTO
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        // Fetch post by ID or throw exception
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // Update post details
        existingPost.setPostTitle(postDTO.getPostTitle());
        existingPost.setContent(postDTO.getContent());
        existingPost.setPostImage(postDTO.getPostImage());

        // Save and return updated post
        Post updatedPost = postRepository.save(existingPost);
        return modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts() {
//        List<Post> posts = postRepository.findAll();
//        return posts.stream().map(modelMapper.map(posts, PostDTO.class)).toList();
        return  null;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        postRepository.delete(post);
    }

    @Override
    public List<Post> getPostByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return postRepository.findByCategory(category);
    }

    @Override
    public List<Post> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return postRepository.findByUser(user);
    }

}
