package it.formicola.congress_be.application.business.internal.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "interazioni_linkedin")
public class LinkedInInteraction {

    @Id
    @Column(name = "partecipante_id")
    private Long participantId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partecipante_id", nullable = false)
    private Participant participant;

    @Column(name = "annuncio_reach", nullable = false)
    private boolean adReach;

    @Column(name = "annuncio_interazione", nullable = false)
    private boolean adInteraction;

    @Column(name = "recap_reach", nullable = false)
    private boolean recapReach;

    @Column(name = "recap_interazione", nullable = false)
    private boolean recapInteraction;

    public LinkedInInteraction(Participant participant, boolean adReach, boolean adInteraction, boolean recapReach, boolean recapInteraction) {
        this.participant = participant;
        this.adReach = adReach;
        this.adInteraction = adInteraction;
        this.recapReach = recapReach;
        this.recapInteraction = recapInteraction;
    }
}
