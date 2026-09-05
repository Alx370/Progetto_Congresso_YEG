package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.Region;

import java.util.List;
import java.util.Optional;

public interface RegionService {

    List<Region> getAllRegions();

    Optional<Region> getRegionById(Long id);

    Optional<Region> getRegionByName(String name);
}
