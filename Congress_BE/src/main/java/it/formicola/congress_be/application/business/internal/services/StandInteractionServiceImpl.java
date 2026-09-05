package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.StandInteraction;
import it.formicola.congress_be.application.business.internal.repository.StandInteractionRepository;
import it.formicola.congress_be.application.business.publishing.StandInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StandInteractionServiceImpl implements StandInteractionService {

    private final StandInteractionRepository standInteractionRepository;

    @Override
    public Optional<StandInteraction> getByParticipantId(Long participantId) {
        return standInteractionRepository.findById(participantId);
    }
}
