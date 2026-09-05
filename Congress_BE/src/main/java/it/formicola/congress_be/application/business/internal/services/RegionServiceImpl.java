package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.Region;
import it.formicola.congress_be.application.business.internal.repository.RegionRepository;
import it.formicola.congress_be.application.business.publishing.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public Optional<Region> getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    @Override
    public Optional<Region> getRegionByName(String name) {
        return regionRepository.findByName(name);
    }
}
