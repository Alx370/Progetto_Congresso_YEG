package it.formicola.congress_be.application.api.controllers;

import it.formicola.congress_be.application.business.publishing.EngagementChannelService;
import it.formicola.congress_be.application.business.publishing.RegionService;
import it.formicola.congress_be.application.business.publishing.StakeholderTypeService;
import it.formicola.congress_be.application.views.item.EngagementChannelItem;
import it.formicola.congress_be.application.views.item.RegionItem;
import it.formicola.congress_be.application.views.item.StakeholderTypeItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reference-data")
public class ReferenceDataController {

    private final RegionService regionService;
    private final StakeholderTypeService stakeholderTypeService;
    private final EngagementChannelService engagementChannelService;

    public ReferenceDataController(RegionService regionService, StakeholderTypeService stakeholderTypeService, EngagementChannelService engagementChannelService) {
        this.regionService = regionService;
        this.stakeholderTypeService = stakeholderTypeService;
        this.engagementChannelService = engagementChannelService;
    }

    @GetMapping("/regions")
    public List<RegionItem> findAllRegions() {
        return regionService.findAll();
    }

    @GetMapping("/stakeholder-types")
    public List<StakeholderTypeItem> findAllStakeholderTypes() {
        return stakeholderTypeService.findAll();
    }

    @GetMapping("/engagement-channels")
    public List<EngagementChannelItem> findAllEngagementChannels() {
        return engagementChannelService.findAll();
    }
}
