package it.formicola.congress_be.application.api.controllers;

import it.formicola.congress_be.application.business.publishing.EmailInteractionService;
import it.formicola.congress_be.application.business.publishing.LinkedInInteractionService;
import it.formicola.congress_be.application.business.publishing.ParticipantService;
import it.formicola.congress_be.application.business.publishing.StandInteractionService;
import it.formicola.congress_be.application.business.publishing.SymposiumInteractionService;
import it.formicola.congress_be.application.business.publishing.VipRoomInteractionService;
import it.formicola.congress_be.application.views.item.ParticipantInteractionsItem;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participants/{participantId}/interactions")
public class ParticipantInteractionsController {

    private final ParticipantService participantService;
    private final EmailInteractionService emailInteractionService;
    private final LinkedInInteractionService linkedInInteractionService;
    private final StandInteractionService standInteractionService;
    private final VipRoomInteractionService vipRoomInteractionService;
    private final SymposiumInteractionService symposiumInteractionService;

    public ParticipantInteractionsController(ParticipantService participantService, EmailInteractionService emailInteractionService, LinkedInInteractionService linkedInInteractionService, StandInteractionService standInteractionService, VipRoomInteractionService vipRoomInteractionService, SymposiumInteractionService symposiumInteractionService) {
        this.participantService = participantService;
        this.emailInteractionService = emailInteractionService;
        this.linkedInInteractionService = linkedInInteractionService;
        this.standInteractionService = standInteractionService;
        this.vipRoomInteractionService = vipRoomInteractionService;
        this.symposiumInteractionService = symposiumInteractionService;
    }

    @GetMapping
    public ParticipantInteractionsItem findByParticipantId(@PathVariable Long participantId) {
        participantService.findById(participantId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Participant not found for id=" + participantId
                ));

        return new ParticipantInteractionsItem(participantId, emailInteractionService.findByParticipantId(participantId).orElse(null), linkedInInteractionService.findByParticipantId(participantId).orElse(null), standInteractionService.findByParticipantId(participantId).orElse(null), vipRoomInteractionService.findByParticipantId(participantId).orElse(null), symposiumInteractionService.findByParticipantId(participantId).orElse(null)
        );
    }
}
