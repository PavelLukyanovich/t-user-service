package ru.clevertec.userservice.usertoken.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        return userTokenRepository.findTokenByToken(token);
    }

    @Override
    @Transactional
    public VerificationToken generateVerificationToken(UUID userId) {
        LocalDateTime now = LocalDateTime.now();
        String token = java.util.UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(userRepository.findById(userId).orElseThrow());
        verificationToken.setExpiryDate(now.plusSeconds(15));
        return verificationToken;
    }

    @Override
    public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
        return userTokenRepository.save(verificationToken);
    }

}
