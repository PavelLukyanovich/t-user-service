package ru.clevertec.userservice.link.service;

import ru.clevertec.userservice.link.domain.ActivationLink;
import ru.clevertec.userservice.usertoken.domain.VerificationToken;

public interface LinkService {
    ActivationLink saveLink(ActivationLink activationLink);
    ActivationLink generateLink(VerificationToken token);
}
