package com.wrc.QueryUs.security;

import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
@NoArgsConstructor
public class QueryUtils {
    public  UserRepository userRepository;
    public  User getCurrentLoggedInUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getByEmail(email).orElseThrow();
        return user;

    }
}
