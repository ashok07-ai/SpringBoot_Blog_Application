package com.blog_application.app.BlogApplication.dto;

import com.blog_application.app.BlogApplication.entities.Category;
import com.blog_application.app.BlogApplication.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostDTO {
    @NotBlank(message = "Post title is required")
    @Size(min = 3, max = 100, message = "Post title must be between 3 and 100 characters")
    private String postTitle;

    @NotBlank(message = "Content is required")
    private String content;

    private String postImage;

    @NotBlank(message = "Created date is required")
    private String createdDate;

    private Category category;

    private User user;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
