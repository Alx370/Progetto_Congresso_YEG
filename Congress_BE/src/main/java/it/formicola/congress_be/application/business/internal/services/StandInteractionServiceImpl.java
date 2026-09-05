package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.StandInteractionRepository;
import it.formicola.congress_be.application.business.publishing.StandInteractionService;
import it.formicola.congress_be.application.views.item.StandInteractionItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StandInteractionServiceImpl implements StandInteractionService {

    private final StandInteractionRepository standInteractionRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<StandInteractionItem> findByParticipantId(Long participantId) {
        return standInteractionRepository.findById(participantId)
                .map(interaction -> mapper.map(interaction, StandInteractionItem.class));
    }
}
