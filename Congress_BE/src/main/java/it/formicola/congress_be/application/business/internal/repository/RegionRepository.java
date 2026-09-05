package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findByName(String name);
}
