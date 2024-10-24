package com.blog_application.app.BlogApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {
    @NotBlank(message = "Category title is required")
    @Size(min = 3, max = 100, message = "Category title must be between 3 and 100 characters")
    private String categoryTitle;

    private  String categoryDescription;

    // Getters and Setters
    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String _categoryTitle) {
        this.categoryTitle = _categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String _categoryDescription) {
        this.categoryDescription = _categoryDescription;
    }
}
