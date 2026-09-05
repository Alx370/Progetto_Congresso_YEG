package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.EngagementChannel;

import java.util.List;
import java.util.Optional;

public interface EngagementChannelService {

    List<EngagementChannel> getAllEngagementChannels();

    Optional<EngagementChannel> getEngagementChannelById(Long id);

    Optional<EngagementChannel> getEngagementChannelByName(String name);
}
