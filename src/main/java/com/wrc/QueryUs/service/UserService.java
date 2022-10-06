package com.wrc.QueryUs.service;

import com.wrc.QueryUs.dto.RegisterDto;
import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDto registerUser(RegisterDto registerDto) {
        User user = userRepository.getByEmail(registerDto.getEmail()).orElse(null);
        if(user!=null){
            throw new RuntimeException("User already exists.");
        }
            user = new User();
            user.setUsername(registerDto.getUsername());
            user.setEmail(registerDto.getEmail());
            user.setReputation(0);
            user.setRole(registerDto.getUserRole());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            return entityToDto(userRepository.save(user));
    }

    private UserDto entityToDto(User user) {
        if(user==null)
            return null;
        UserDto u = new UserDto();
        u.setUsername(user.getUsername());
        u.setId(user.getId());
        u.setEmail(user.getEmail());
        u.setReputation(user.getReputation());
        u.setRole(user.getRole());
        return u;

    }
    public UserDto getUser(int id){
        User user = userRepository.findById(id).orElse(null);
        return entityToDto(user);
    }


}
