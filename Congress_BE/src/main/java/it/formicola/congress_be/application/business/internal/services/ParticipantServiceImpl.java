package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.Participant;
import it.formicola.congress_be.application.business.internal.repository.ParticipantRepository;
import it.formicola.congress_be.application.business.publishing.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    @Override
    public Optional<Participant> getParticipantByEmail(String email) {
        return participantRepository.findByEmail(email);
    }
}
