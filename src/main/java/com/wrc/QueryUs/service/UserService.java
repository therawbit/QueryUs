package com.wrc.QueryUs.service;


import com.wrc.QueryUs.dto.ApiResponse;
import com.wrc.QueryUs.dto.RegisterDto;
import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.entity.VerificationToken;
import com.wrc.QueryUs.repository.TokenRepository;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    @Transactional
    public void registerUser(RegisterDto registerDto) {
        User user = userRepository.getByEmail(registerDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeException("User already exists.");
        }
        user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setMiddleName(registerDto.getMiddleName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setReputation(0);
        user.setRole(registerDto.getUserRole());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        User a = userRepository.save(user);
        generateToken(a);
    }

    private void generateToken(User a) {
        String token = UUID.randomUUID().toString();
        saveToken(token,a);

    }

    private UserDto entityToDto(User user) {
        if (user == null)
            return null;
        UserDto u = new UserDto();
        BeanUtils.copyProperties(user, u);
        return u;

    }

    public UserDto getUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not found"));
        return entityToDto(user);
    }


    public void enableUser(String email) {
        User user = userRepository.getByEmail(email).orElseThrow(()->new UsernameNotFoundException("User does not exist."));
        user.setEnabled(true);
        userRepository.save(user);
    }
    private void saveToken(String t, User user){
        VerificationToken token = new VerificationToken();
        token.setToken(t);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        token.setUser(user);
        tokenRepository.save(token);

    }

}
