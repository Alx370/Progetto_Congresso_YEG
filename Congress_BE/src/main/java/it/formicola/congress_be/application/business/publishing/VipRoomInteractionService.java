package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.VipRoomInteractionItem;

import java.util.Optional;

public interface VipRoomInteractionService {

    Optional<VipRoomInteractionItem> findByParticipantId(Long participantId);
}
