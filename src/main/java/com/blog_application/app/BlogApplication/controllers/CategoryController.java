package com.blog_application.app.BlogApplication.controllers;

import com.blog_application.app.BlogApplication.dto.CategoryDTO;
import com.blog_application.app.BlogApplication.services.CategoryService;
import com.blog_application.app.BlogApplication.utlis.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<CategoryDTO>> _createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return buildResponse(HttpStatus.CREATED, "Category created successfully!", createdCategory);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDTO>> _updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO, categoryId);
        return buildResponse(HttpStatus.OK, String.format("Category with ID: %d updated successfully!", categoryId), updatedCategory);

    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAllCategory(){
        List<CategoryDTO> allCategories = categoryService.getAllCategories();
        return buildResponse(HttpStatus.OK, "Category details fetched successfully", allCategories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDTO>> _getCategoryById(@PathVariable Integer categoryId){
        CategoryDTO getCategoryByIdDetails = categoryService.getCategoryById(categoryId);
        return buildResponse(HttpStatus.OK, String.format("Category with ID: %d fetched successfully!", categoryId), getCategoryByIdDetails);

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<Void>> _deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return buildResponse(HttpStatus.OK, "Category deleted successfully", null);
    }


    private <T> ResponseEntity<ApiResponse<T>> buildResponse(HttpStatus status, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(message, data);
        return new ResponseEntity<>(response, status);
    }

}
