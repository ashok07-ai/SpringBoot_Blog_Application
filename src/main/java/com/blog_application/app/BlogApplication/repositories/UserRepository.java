package com.blog_application.app.BlogApplication.repositories;

import com.blog_application.app.BlogApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
