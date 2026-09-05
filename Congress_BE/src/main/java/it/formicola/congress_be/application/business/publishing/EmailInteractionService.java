package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.EmailInteractionItem;

import java.util.Optional;

public interface EmailInteractionService {

    Optional<EmailInteractionItem> findByParticipantId(Long participantId);
}
