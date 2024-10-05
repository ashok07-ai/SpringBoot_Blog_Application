package com.blog_application.app.BlogApplication.services;

import com.blog_application.app.BlogApplication.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    /**
     * Registers a new category in the system.
     * @param category The category data transfer object (DTO) containing the category's details.
     * @return The registered category information after successful creation.
     */
    CategoryDTO createCategory(CategoryDTO category);

    /**
     * Updates an existing category's information.
     * @param category The updated category DTO with new information.
     * @return The updated category data after successful update.
     */
    CategoryDTO updateCategory(CategoryDTO category, Integer categoryId);

    /**
     * Retrieves the details of a category based on the provided category ID.
     * @param categoryId The unique identifier of the category to fetch.
     * @return The category DTO containing the category's information.
     */
    CategoryDTO getCategoryById(Integer categoryId);

    /**
     * Retrieves a list of all categories present in the system.
     * @return A list of CategoryDTOs representing all registered categories.
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Deletes a category from the system based on their ID.
     * @param categoryId The unique identifier of the category to be deleted.
     */
    void deleteCategory(Integer categoryId);

}
