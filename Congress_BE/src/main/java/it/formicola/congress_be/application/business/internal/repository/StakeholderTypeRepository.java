package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.StakeholderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StakeholderTypeRepository extends JpaRepository<StakeholderType, Long> {

    Optional<StakeholderType> findByName(String name);
}
