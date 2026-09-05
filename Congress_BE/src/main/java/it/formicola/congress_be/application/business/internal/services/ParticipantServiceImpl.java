package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.ParticipantRepository;
import it.formicola.congress_be.application.business.publishing.ParticipantService;
import it.formicola.congress_be.application.views.item.ParticipantItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ModelMapper mapper;

    @Override
    public Page<ParticipantItem> findAll(Pageable pageable) {
        return participantRepository.findAll(pageable)
                .map(participant -> mapper.map(participant, ParticipantItem.class));
    }

    @Override
    public Optional<ParticipantItem> findById(Long id) {
        return participantRepository.findById(id)
                .map(participant -> mapper.map(participant, ParticipantItem.class));
    }

    @Override
    public Optional<ParticipantItem> findByEmail(String email) {
        return participantRepository.findByEmail(email)
                .map(participant -> mapper.map(participant, ParticipantItem.class));
    }
}
