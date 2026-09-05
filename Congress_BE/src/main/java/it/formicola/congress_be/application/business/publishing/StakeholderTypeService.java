package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.views.item.StakeholderTypeItem;

import java.util.List;
import java.util.Optional;

public interface StakeholderTypeService {

    List<StakeholderTypeItem> findAll();

    Optional<StakeholderTypeItem> findById(Long id);

    Optional<StakeholderTypeItem> findByName(String name);
}
