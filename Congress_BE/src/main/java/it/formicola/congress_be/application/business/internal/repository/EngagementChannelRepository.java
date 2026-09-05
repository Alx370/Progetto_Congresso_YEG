package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.EngagementChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EngagementChannelRepository extends JpaRepository<EngagementChannel, Long> {

    Optional<EngagementChannel> findByName(String name);
}
