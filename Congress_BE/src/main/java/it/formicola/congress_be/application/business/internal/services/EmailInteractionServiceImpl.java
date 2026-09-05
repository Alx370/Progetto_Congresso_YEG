package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.EmailInteractionRepository;
import it.formicola.congress_be.application.business.publishing.EmailInteractionService;
import it.formicola.congress_be.application.views.item.EmailInteractionItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmailInteractionServiceImpl implements EmailInteractionService {

    private final EmailInteractionRepository emailInteractionRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<EmailInteractionItem> findByParticipantId(Long participantId) {
        return emailInteractionRepository.findById(participantId)
                .map(interaction -> mapper.map(interaction, EmailInteractionItem.class));
    }
}
