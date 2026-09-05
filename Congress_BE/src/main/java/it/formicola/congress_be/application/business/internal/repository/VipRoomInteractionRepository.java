package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.VipRoomInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VipRoomInteractionRepository extends JpaRepository<VipRoomInteraction, Long> {
}
