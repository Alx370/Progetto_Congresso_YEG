package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.SymposiumInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymposiumInteractionRepository extends JpaRepository<SymposiumInteraction, Long> {
}
