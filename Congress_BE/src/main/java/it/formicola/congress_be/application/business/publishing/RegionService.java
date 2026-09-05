package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.RegionItem;

import java.util.List;
import java.util.Optional;

public interface RegionService {

    List<RegionItem> findAll();

    Optional<RegionItem> findById(Long id);

    Optional<RegionItem> findByName(String name);
}
