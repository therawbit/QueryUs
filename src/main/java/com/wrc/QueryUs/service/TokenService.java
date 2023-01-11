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
    private final UserService userService;


    public String confirmToken(String token){
        VerificationToken confirmationToken = tokenRepository.findByToken(token)
                .orElseThrow(()->new IllegalArgumentException("Invalid Token"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        tokenRepository.save(confirmationToken);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }

}
