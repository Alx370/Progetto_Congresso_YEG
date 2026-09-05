package it.formicola.congress_be.application.business.internal.services;

import it.formicola.congress_be.application.business.internal.repository.StakeholderTypeRepository;
import it.formicola.congress_be.application.business.publishing.StakeholderTypeService;
import it.formicola.congress_be.application.views.item.StakeholderTypeItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StakeholderTypeServiceImpl implements StakeholderTypeService {

    private final StakeholderTypeRepository stakeholderTypeRepository;
    private final ModelMapper mapper;

    @Override
    public List<StakeholderTypeItem> findAll() {
        return stakeholderTypeRepository.findAll().stream()
                .map(type -> mapper.map(type, StakeholderTypeItem.class))
                .toList();
    }

    @Override
    public Optional<StakeholderTypeItem> findById(Long id) {
        return stakeholderTypeRepository.findById(id)
                .map(type -> mapper.map(type, StakeholderTypeItem.class));
    }

    @Override
    public Optional<StakeholderTypeItem> findByName(String name) {
        return stakeholderTypeRepository.findByName(name)
                .map(type -> mapper.map(type, StakeholderTypeItem.class));
    }
}
