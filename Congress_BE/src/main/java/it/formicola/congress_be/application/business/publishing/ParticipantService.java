package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {

    List<Participant> getAllParticipants();

    Optional<Participant> getParticipantById(Long id);

    Optional<Participant> getParticipantByEmail(String email);
}
