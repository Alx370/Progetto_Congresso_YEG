package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.EngagementChannel;
import it.formicola.congress_be.application.business.internal.repository.EngagementChannelRepository;
import it.formicola.congress_be.application.business.publishing.EngagementChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EngagementChannelServiceImpl implements EngagementChannelService {

    private final EngagementChannelRepository engagementChannelRepository;

    @Override
    public List<EngagementChannel> getAllEngagementChannels() {
        return engagementChannelRepository.findAll();
    }

    @Override
    public Optional<EngagementChannel> getEngagementChannelById(Long id) {
        return engagementChannelRepository.findById(id);
    }

    @Override
    public Optional<EngagementChannel> getEngagementChannelByName(String name) {
        return engagementChannelRepository.findByName(name);
    }
}
