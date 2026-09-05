package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.EmailInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailInteractionRepository extends JpaRepository<EmailInteraction, Long> {
}
