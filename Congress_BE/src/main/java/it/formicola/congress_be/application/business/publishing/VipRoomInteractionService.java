package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.VipRoomInteraction;

import java.util.Optional;

public interface VipRoomInteractionService {

    Optional<VipRoomInteraction> getByParticipantId(Long participantId);
}
