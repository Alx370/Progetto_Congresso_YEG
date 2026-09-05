package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.LinkedInInteractionItem;

import java.util.Optional;

public interface LinkedInInteractionService {

    Optional<LinkedInInteractionItem> findByParticipantId(Long participantId);
}
