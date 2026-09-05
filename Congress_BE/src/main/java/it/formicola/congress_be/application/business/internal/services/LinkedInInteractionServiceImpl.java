package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.LinkedInInteraction;
import it.formicola.congress_be.application.business.internal.repository.LinkedInInteractionRepository;
import it.formicola.congress_be.application.business.publishing.LinkedInInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LinkedInInteractionServiceImpl implements LinkedInInteractionService {

    private final LinkedInInteractionRepository linkedInInteractionRepository;

    @Override
    public Optional<LinkedInInteraction> getByParticipantId(Long participantId) {
        return linkedInInteractionRepository.findById(participantId);
    }
}
