package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.VipRoomInteraction;
import it.formicola.congress_be.application.business.internal.repository.VipRoomInteractionRepository;
import it.formicola.congress_be.application.business.publishing.VipRoomInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class VipRoomInteractionServiceImpl implements VipRoomInteractionService {

    private final VipRoomInteractionRepository vipRoomInteractionRepository;

    @Override
    public Optional<VipRoomInteraction> getByParticipantId(Long participantId) {
        return vipRoomInteractionRepository.findById(participantId);
    }
}
