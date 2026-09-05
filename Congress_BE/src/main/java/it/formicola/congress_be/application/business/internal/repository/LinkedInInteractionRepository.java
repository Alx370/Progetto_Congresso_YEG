package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.LinkedInInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkedInInteractionRepository extends JpaRepository<LinkedInInteraction, Long> {
}
