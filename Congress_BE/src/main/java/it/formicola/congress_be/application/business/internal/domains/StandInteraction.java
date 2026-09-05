package it.formicola.congress_be.application.business.internal.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "interazioni_stand")
public class StandInteraction {

    @Id
    @Column(name = "partecipante_id")
    @Setter(AccessLevel.NONE)
    private Long participantId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partecipante_id", nullable = false)
    private Participant participant;

    @Column(name = "visita_stand", nullable = false)
    private boolean standVisited;

    @Column(name = "giorno_visita")
    private LocalDate visitDate;

    @Column(name = "visualizzazioni", nullable = false)
    private int views;

    @Column(name = "scroll", nullable = false)
    private int scroll;

    public StandInteraction(Participant participant, boolean standVisited, LocalDate visitDate, int views, int scroll) {
        this.participant = participant;
        this.standVisited = standVisited;
        this.visitDate = visitDate;
        this.views = views;
        this.scroll = scroll;
    }
}
