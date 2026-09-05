package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.EmailInteraction;

import java.util.Optional;

public interface EmailInteractionService {

    Optional<EmailInteraction> getByParticipantId(Long participantId);
}
