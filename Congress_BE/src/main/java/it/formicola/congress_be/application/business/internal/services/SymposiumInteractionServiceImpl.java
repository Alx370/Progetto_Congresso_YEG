package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.SymposiumInteraction;
import it.formicola.congress_be.application.business.internal.repository.SymposiumInteractionRepository;
import it.formicola.congress_be.application.business.publishing.SymposiumInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SymposiumInteractionServiceImpl implements SymposiumInteractionService {

    private final SymposiumInteractionRepository symposiumInteractionRepository;

    @Override
    public Optional<SymposiumInteraction> getByParticipantId(Long participantId) {
        return symposiumInteractionRepository.findById(participantId);
    }
}
