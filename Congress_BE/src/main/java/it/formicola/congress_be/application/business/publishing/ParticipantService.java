package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.ParticipantItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ParticipantService {

    Page<ParticipantItem> findAll(Pageable pageable);

    Optional<ParticipantItem> findById(Long id);

    Optional<ParticipantItem> findByEmail(String email);
}
