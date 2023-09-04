package ru.clevertec.userservice.link.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.userservice.link.domain.ActivationLink;
import ru.clevertec.userservice.link.repository.LinkRepository;
import ru.clevertec.userservice.link.service.LinkService;
import ru.clevertec.userservice.usertoken.domain.VerificationToken;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;

    @Override
    public ActivationLink saveLink(ActivationLink link) {
        log.info("Link is sanded {}", link);
        return linkRepository.save(link);
    }

    @Override
    public ActivationLink generateLink(VerificationToken token) {
        var activationLink = new ActivationLink();
        activationLink.setLink("http://localhost:8085/api/v1/authentication/registrationConfirm?token=" + token.getToken());
        return activationLink;
    }
}
