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

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "interazioni_simposio")
public class SymposiumInteraction {

    @Id
    @Column(name = "partecipante_id")
    private Long participantId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partecipante_id", nullable = false)
    private Participant participant;

    @Column(name = "presenza_simposio", nullable = false)
    private boolean symposiumAttendance;

    @Column(name = "permanenza_min")
    private Integer durationMinutes;

    @Column(name = "focus_rate", precision = 5, scale = 4)
    private BigDecimal focusRate;

    @Column(name = "quiz_completati", nullable = false)
    private int completedQuizzes;

    public SymposiumInteraction(
            Participant participant,
            boolean symposiumAttendance,
            Integer durationMinutes,
            BigDecimal focusRate,
            int completedQuizzes
    ) {
        this.participant = participant;
        this.symposiumAttendance = symposiumAttendance;
        this.durationMinutes = durationMinutes;
        this.focusRate = focusRate;
        this.completedQuizzes = completedQuizzes;
    }
}
