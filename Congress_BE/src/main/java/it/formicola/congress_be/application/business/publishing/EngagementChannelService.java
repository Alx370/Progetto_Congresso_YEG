package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.EngagementChannelItem;

import java.util.List;
import java.util.Optional;

public interface EngagementChannelService {

    List<EngagementChannelItem> findAll();

    Optional<EngagementChannelItem> findById(Long id);

    Optional<EngagementChannelItem> findByName(String name);
}
