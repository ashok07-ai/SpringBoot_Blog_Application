package com.blog_application.app.BlogApplication.controllers;

import com.blog_application.app.BlogApplication.dto.PostDTO;
import com.blog_application.app.BlogApplication.dto.UserDTO;
import com.blog_application.app.BlogApplication.entities.Post;
import com.blog_application.app.BlogApplication.services.FileService;
import com.blog_application.app.BlogApplication.services.PostService;
import com.blog_application.app.BlogApplication.utlis.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<ApiResponse<PostDTO>> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDTO createdPost = postService.createPost(postDTO, userId, categoryId);
        return buildResponse(HttpStatus.CREATED, "Post created successfully!", createdPost);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO postDTO = postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(postDTO, HttpStatus.OK);
    }

    private <T> ResponseEntity<ApiResponse<T>> buildResponse(HttpStatus status, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(message, data);
        return new ResponseEntity<>(response, status);
    }


    // Post image upload
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
        PostDTO postDTO = postService.getPostById(postId);
        String fileName = fileService.uploadImage(path, image);
        postDTO.setPostImage(fileName);
        PostDTO updatedPost = postService.updatePost(postDTO, postId);
        return new ResponseEntity<PostDTO>(updatedPost, HttpStatus.OK);
    }
}
