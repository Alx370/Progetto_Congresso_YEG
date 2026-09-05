package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.domains.StakeholderType;
import it.formicola.congress_be.application.business.internal.repository.StakeholderTypeRepository;
import it.formicola.congress_be.application.business.publishing.StakeholderTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StakeholderTypeServiceImpl implements StakeholderTypeService {

    private final StakeholderTypeRepository stakeholderTypeRepository;

    @Override
    public List<StakeholderType> getAllStakeholderTypes() {
        return stakeholderTypeRepository.findAll();
    }

    @Override
    public Optional<StakeholderType> getStakeholderTypeById(Long id) {
        return stakeholderTypeRepository.findById(id);
    }

    @Override
    public Optional<StakeholderType> getStakeholderTypeByName(String name) {
        return stakeholderTypeRepository.findByName(name);
    }
}
