package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.EngagementChannelRepository;
import it.formicola.congress_be.application.business.publishing.EngagementChannelService;
import it.formicola.congress_be.application.views.item.EngagementChannelItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EngagementChannelServiceImpl implements EngagementChannelService {

    private final EngagementChannelRepository engagementChannelRepository;
    private final ModelMapper mapper;

    @Override
    public List<EngagementChannelItem> findAll() {
        return engagementChannelRepository.findAll().stream()
                .map(channel -> mapper.map(channel, EngagementChannelItem.class))
                .toList();
    }

    @Override
    public Optional<EngagementChannelItem> findById(Long id) {
        return engagementChannelRepository.findById(id)
                .map(channel -> mapper.map(channel, EngagementChannelItem.class));
    }

    @Override
    public Optional<EngagementChannelItem> findByName(String name) {
        return engagementChannelRepository.findByName(name)
                .map(channel -> mapper.map(channel, EngagementChannelItem.class));
    }
}
