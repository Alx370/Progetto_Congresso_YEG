package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.StandInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandInteractionRepository extends JpaRepository<StandInteraction, Long> {
}
