package ru.clevertec.userservice.usertoken.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.userservice.exception.appException.ExpireDateException;
import ru.clevertec.userservice.user.repository.UserRepository;
import ru.clevertec.userservice.usertoken.domain.VerificationToken;
import ru.clevertec.userservice.usertoken.repository.UserTokenRepository;
import ru.clevertec.userservice.usertoken.service.UserTokenService;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTokenServiceImpl implements UserTokenService {

    private final UserTokenRepository userTokenRepository;
    private final UserRepository userRepository;

    @Override
    public VerificationToken getVerificationToken(String token) {
        var tokenByToken = userTokenRepository.findTokenByToken(token);
        if (!tokenByToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return tokenByToken;
        } else {
            throw new ExpireDateException("token was expired");
        }
    }

    @Override
    public VerificationToken generateVerificationToken(UUID userId) {
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        return VerificationToken.builder()
                .token(token)
                .user(userRepository.findById(userId).orElseThrow())
                .expiryDate(now.plusHours(24))
                .build();
    }

    @Override
    public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
        return userTokenRepository.save(verificationToken);
    }

}
