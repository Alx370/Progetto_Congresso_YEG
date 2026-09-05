package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.StandInteractionItem;

import java.util.Optional;

public interface StandInteractionService {

    Optional<StandInteractionItem> findByParticipantId(Long participantId);
}
