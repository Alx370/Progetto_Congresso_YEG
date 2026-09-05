package it.formicola.congress_be.application.business.internal.repository;

import it.formicola.congress_be.application.business.internal.domains.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    @Override
    @EntityGraph(attributePaths = {"stakeholderType", "region", "engagementChannel"})
    Page<Participant> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"stakeholderType", "region", "engagementChannel"})
    Optional<Participant> findById(Long id);

    @EntityGraph(attributePaths = {"stakeholderType", "region", "engagementChannel"})
    Optional<Participant> findByEmail(String email);

    boolean existsByEmail(String email);
}
