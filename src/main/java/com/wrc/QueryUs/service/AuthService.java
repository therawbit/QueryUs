package com.wrc.QueryUs.service;

import com.wrc.QueryUs.dto.RegisterDto;
import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDto registerUser(RegisterDto registerDto) {
        User user = userRepository.getByEmail(registerDto.getEmail()).orElse(null);
        if (user == null) {
            user = new User();
            user.setUsername(registerDto.getUsername());
            user.setEmail(registerDto.getEmail());
            user.setReputation(0);
            user.setRole(registerDto.getUserRole());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            return entityToDto(userRepository.save(user));
        } else {
            throw new RuntimeException("user already Exist");
        }
    }

    private UserDto entityToDto(User user) {
        UserDto u = new UserDto();
        u.setId(user.getId());
        u.setEmail(user.getEmail());
        u.setReputation(user.getReputation());
        u.setRole(user.getRole());
        return u;

    }

}
