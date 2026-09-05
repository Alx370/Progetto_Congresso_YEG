package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.SymposiumInteractionItem;

import java.util.Optional;

public interface SymposiumInteractionService {

    Optional<SymposiumInteractionItem> findByParticipantId(Long participantId);
}
