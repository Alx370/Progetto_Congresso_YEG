package it.formicola.congress_be.application.business.publishing;

import it.formicola.congress_be.application.business.internal.domains.StakeholderType;

import java.util.List;
import java.util.Optional;

public interface StakeholderTypeService {

    List<StakeholderType> getAllStakeholderTypes();

    Optional<StakeholderType> getStakeholderTypeById(Long id);

    Optional<StakeholderType> getStakeholderTypeByName(String name);
}
