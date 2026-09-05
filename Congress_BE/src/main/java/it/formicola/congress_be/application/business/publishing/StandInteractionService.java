package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.StandInteraction;

import java.util.Optional;

public interface StandInteractionService {

    Optional<StandInteraction> getByParticipantId(Long participantId);
}
