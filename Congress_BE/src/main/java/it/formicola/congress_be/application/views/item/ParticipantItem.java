package it.formicola.congress_be.application.views.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantItem {

    private Long id;
    private Long excelId;
    private String fullName;
    private String email;
    private StakeholderTypeItem stakeholderType;
    private RegionItem region;
    private EngagementChannelItem engagementChannel;
    private boolean inDemDatabase;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
