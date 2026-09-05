package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.RegionRepository;
import it.formicola.congress_be.application.business.publishing.RegionService;
import it.formicola.congress_be.application.views.item.RegionItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final ModelMapper mapper;

    @Override
    public List<RegionItem> findAll() {
        return regionRepository.findAll().stream()
                .map(region -> mapper.map(region, RegionItem.class))
                .toList();
    }

    @Override
    public Optional<RegionItem> findById(Long id) {
        return regionRepository.findById(id)
                .map(region -> mapper.map(region, RegionItem.class));
    }

    @Override
    public Optional<RegionItem> findByName(String name) {
        return regionRepository.findByName(name)
                .map(region -> mapper.map(region, RegionItem.class));
    }
}
