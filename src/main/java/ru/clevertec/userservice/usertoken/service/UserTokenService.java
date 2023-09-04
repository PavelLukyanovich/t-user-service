package ru.clevertec.userservice.usertoken.service;

import ru.clevertec.userservice.usertoken.domain.VerificationToken;

import java.util.UUID;

public interface UserTokenService {

    VerificationToken getVerificationToken(String token);

    VerificationToken generateVerificationToken(UUID User);

    VerificationToken saveVerificationToken(VerificationToken verificationToken);

}
