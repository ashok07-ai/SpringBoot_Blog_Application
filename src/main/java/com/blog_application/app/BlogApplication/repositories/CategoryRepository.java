package com.blog_application.app.BlogApplication.repositories;

import com.blog_application.app.BlogApplication.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
