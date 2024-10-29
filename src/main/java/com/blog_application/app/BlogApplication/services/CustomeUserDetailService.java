package com.blog_application.app.BlogApplication.services;

import com.blog_application.app.BlogApplication.entities.User;
import com.blog_application.app.BlogApplication.repositories.UserRepository;
import com.blog_application.app.BlogApplication.utlis.AuthDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomeUserDetailService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                logger.error("User with username '{}' not found in the database.", username);
                throw new UsernameNotFoundException("User with username '" + username + "' not found.");
            }
            return new AuthDetails(user);
        } catch (Exception ex) {
            logger.error("An error occurred while loading user with username '{}': {}", username, ex.getMessage());
            throw new UsernameNotFoundException("Failed to load user with username '" + username + "'", ex);
        }
    }
}
