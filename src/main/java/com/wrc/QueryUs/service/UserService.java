package com.wrc.QueryUs.service;


import com.wrc.QueryUs.dto.RegisterDto;
import com.wrc.QueryUs.dto.UserDto;
import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.entity.VerificationToken;
import com.wrc.QueryUs.repository.TokenRepository;
import com.wrc.QueryUs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private EmailService emailService;
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
        generateToken(a,true);
    }

    private void generateToken(User user,boolean isRegistration) {
        String token = UUID.randomUUID().toString();
        saveToken(token,user,isRegistration);

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
    private void saveToken(String t, User user,boolean isRegistration){
        VerificationToken token = new VerificationToken();
        token.setToken(t);
        token.setExpiresAt(LocalDateTime.now().plusHours(1));
        token.setUser(user);
        tokenRepository.save(token);
        if(isRegistration)
            emailService.sendActivationEmail(user.getEmail(),user.getFirstName(),t);
        else
            emailService.sendPasswordResetEmail(user.getEmail(),user.getFirstName(),t);
    }

//    private void sendMail(String email,String firstName,String token) {
//        emailService.sendActivationEmail(email,firstName,token);
//    }


    public void resendToken(String email) {
        User u = userRepository.getByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email do not exist."));
        if(u.isEnabled())
            throw new RuntimeException("User Already Activated");
        else
            generateToken(u,true);
    }
    public void resetPasswordToken(String email){
        User user = userRepository.getByEmail(email).orElseThrow(()-> new RuntimeException("Email does not exist."));
        generateToken(user,false);
    }
}
