package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.LinkedInInteraction;

import java.util.Optional;

public interface LinkedInInteractionService {

    Optional<LinkedInInteraction> getByParticipantId(Long participantId);
}
