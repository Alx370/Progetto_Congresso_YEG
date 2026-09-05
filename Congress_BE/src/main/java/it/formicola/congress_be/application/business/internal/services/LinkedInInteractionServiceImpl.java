package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.LinkedInInteractionRepository;
import it.formicola.congress_be.application.business.publishing.LinkedInInteractionService;
import it.formicola.congress_be.application.views.item.LinkedInInteractionItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkedInInteractionServiceImpl implements LinkedInInteractionService {

    private final LinkedInInteractionRepository linkedInInteractionRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<LinkedInInteractionItem> findByParticipantId(Long participantId) {
        return linkedInInteractionRepository.findById(participantId)
                .map(interaction -> mapper.map(interaction, LinkedInInteractionItem.class));
    }
}
