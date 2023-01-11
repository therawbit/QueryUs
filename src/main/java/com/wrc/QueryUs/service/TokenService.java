package com.wrc.QueryUs.service;

import com.wrc.QueryUs.entity.User;
import com.wrc.QueryUs.entity.VerificationToken;
import com.wrc.QueryUs.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public void saveToken(String t, User user){
        VerificationToken token = new VerificationToken();
        token.setToken(t);
        token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        token.setUser(user);
        tokenRepository.save(token);

    }

}
