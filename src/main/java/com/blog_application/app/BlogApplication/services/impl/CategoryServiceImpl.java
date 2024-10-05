package com.blog_application.app.BlogApplication.services.impl;

import com.blog_application.app.BlogApplication.dto.CategoryDTO;
import com.blog_application.app.BlogApplication.entities.Category;
import com.blog_application.app.BlogApplication.exceptions.ResourceNotFoundException;
import com.blog_application.app.BlogApplication.repositories.CategoryRepository;
import com.blog_application.app.BlogApplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Save and return the new category as DTO
        Category category = categoryRepository.save(mapToEntity(categoryDTO));
        return mapToDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        // Fetch existing category or throw an exception
        Category categoryDetails = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        // Update category details
        updateCategoryDetails(categoryDetails, categoryDTO);

        // Save updated category and return as DTO
        Category updateCategoryDetails = categoryRepository.save(categoryDetails);
        return mapToDTO(updateCategoryDetails);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        // Fetch category by ID or throw exception
        Category categoryDetails = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        // Return as DTO
        return mapToDTO(categoryDetails);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        // Fetch all categories and convert to DTO list
        return categoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        // Fetch category or throw exception, then delete
        Category categoryDetails = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(categoryDetails);
    }

    // Helper method to map CategoryDTO to Category entity
    private Category mapToEntity(CategoryDTO categoryDTO) {
        // Using Model Mapper
        return modelMapper.map(categoryDTO, Category.class);
    }

    // Helper method to map Category entity to CategoryDTO
    private CategoryDTO mapToDTO(Category category) {
        // Using Model Mapper
        return modelMapper.map(category, CategoryDTO.class);
    }

    // Helper method to update existing category details
    private void updateCategoryDetails(Category categoryDetails, CategoryDTO categoryDTO) {
       categoryDetails.setCategoryTitle(categoryDTO.getCategoryTitle());
       categoryDetails.setCategoryDescription(categoryDTO.getCategoryDescription());

    }
}
