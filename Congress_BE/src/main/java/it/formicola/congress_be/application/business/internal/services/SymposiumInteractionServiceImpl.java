package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.SymposiumInteractionRepository;
import it.formicola.congress_be.application.business.publishing.SymposiumInteractionService;
import it.formicola.congress_be.application.views.item.SymposiumInteractionItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SymposiumInteractionServiceImpl implements SymposiumInteractionService {

    private final SymposiumInteractionRepository symposiumInteractionRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<SymposiumInteractionItem> findByParticipantId(Long participantId) {
        return symposiumInteractionRepository.findById(participantId)
                .map(interaction -> mapper.map(interaction, SymposiumInteractionItem.class));
    }
}
