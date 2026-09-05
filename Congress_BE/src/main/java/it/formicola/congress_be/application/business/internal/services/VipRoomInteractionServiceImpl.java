package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.VipRoomInteractionRepository;
import it.formicola.congress_be.application.business.publishing.VipRoomInteractionService;
import it.formicola.congress_be.application.views.item.VipRoomInteractionItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VipRoomInteractionServiceImpl implements VipRoomInteractionService {

    private final VipRoomInteractionRepository vipRoomInteractionRepository;
    private final ModelMapper mapper;

    @Override
    public Optional<VipRoomInteractionItem> findByParticipantId(Long participantId) {
        return vipRoomInteractionRepository.findById(participantId)
                .map(interaction -> mapper.map(interaction, VipRoomInteractionItem.class));
    }
}
