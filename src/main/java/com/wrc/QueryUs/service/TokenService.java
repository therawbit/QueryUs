package com.wrc.QueryUs.service;

import com.wrc.QueryUs.dto.PasswordResetDto;
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


    public void confirmUser(String token){
       VerificationToken confirmationToken = confirm(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
    }
    public void confirmPassword(PasswordResetDto dto){
        VerificationToken confirmationToken = confirm(dto.getToken());
        userService.changePassword(confirmationToken.getUser(),dto.getPassword());
    }
    private VerificationToken confirm(String token){
        VerificationToken confirmationToken = tokenRepository.findByToken(token)
                .orElseThrow(()->new IllegalArgumentException("Invalid Token"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Token is Already Used");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        tokenRepository.save(confirmationToken);
        return confirmationToken;
    }

}
