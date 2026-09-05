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
@Table(name = "interazioni_email")
public class EmailInteraction {

    @Id
    @Column(name = "partecipante_id")
    private Long participantId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partecipante_id", nullable = false)
    private Participant participant;

    @Column(name = "dem_inviata", nullable = false)
    private boolean demSent;

    @Column(name = "dem_consegnata", nullable = false)
    private boolean demDelivered;

    @Column(name = "dem_aperta", nullable = false)
    private boolean demOpened;

    public EmailInteraction(Participant participant, boolean demSent, boolean demDelivered, boolean demOpened) {
        this.participant = participant;
        this.demSent = demSent;
        this.demDelivered = demDelivered;
        this.demOpened = demOpened;
    }
}
