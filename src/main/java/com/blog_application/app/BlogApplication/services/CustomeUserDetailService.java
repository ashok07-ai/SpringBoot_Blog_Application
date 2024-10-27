package com.blog_application.app.BlogApplication.services;

import com.blog_application.app.BlogApplication.entities.User;
import com.blog_application.app.BlogApplication.repositories.UserRepository;
import com.blog_application.app.BlogApplication.utlis.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user == null){
            System.out.println("Username not found!");
            throw new UsernameNotFoundException("Username not found");
        }
        return new AuthDetails(user);
    }
}
