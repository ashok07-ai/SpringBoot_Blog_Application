package com.blog_application.app.BlogApplication.repositories;

import com.blog_application.app.BlogApplication.entities.Category;
import com.blog_application.app.BlogApplication.entities.Post;
import com.blog_application.app.BlogApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
