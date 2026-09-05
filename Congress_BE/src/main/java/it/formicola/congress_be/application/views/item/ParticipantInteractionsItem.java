package it.formicola.congress_be.application.views.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantInteractionsItem {

    private Long participantId;
    private EmailInteractionItem email;
    private LinkedInInteractionItem linkedIn;
    private StandInteractionItem stand;
    private VipRoomInteractionItem vipRoom;
    private SymposiumInteractionItem symposium;
}
