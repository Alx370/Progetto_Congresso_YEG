package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.SymposiumInteraction;

import java.util.Optional;

public interface SymposiumInteractionService {

    Optional<SymposiumInteraction> getByParticipantId(Long participantId);
}
