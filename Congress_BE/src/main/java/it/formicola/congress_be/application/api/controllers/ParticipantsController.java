package it.formicola.congress_be.application.api.controllers;

import it.formicola.congress_be.application.business.publishing.ParticipantService;
import it.formicola.congress_be.application.views.item.ParticipantItem;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participants")
public class ParticipantsController {

    private final ParticipantService participantService;

    public ParticipantsController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public Page<ParticipantItem> findAll(Pageable pageable) {
        return participantService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ParticipantItem findById(@PathVariable Long id) {
        return participantService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Participant not found for id=" + id));
    }

    @GetMapping("/by-email")
    public ParticipantItem findByEmail(@RequestParam String email) {
        return participantService.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Participant not found for email=" + email));
    }
}
