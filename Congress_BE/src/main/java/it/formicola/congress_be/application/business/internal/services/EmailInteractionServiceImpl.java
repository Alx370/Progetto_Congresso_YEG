package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.EmailInteraction;
import it.formicola.congress_be.application.business.internal.repository.EmailInteractionRepository;
import it.formicola.congress_be.application.business.publishing.EmailInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailInteractionServiceImpl implements EmailInteractionService {

    private final EmailInteractionRepository emailInteractionRepository;

    @Override
    public Optional<EmailInteraction> getByParticipantId(Long participantId) {
        return emailInteractionRepository.findById(participantId);
    }
}
