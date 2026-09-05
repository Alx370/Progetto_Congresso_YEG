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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "interazioni_sala_vip")
public class VipRoomInteraction {

    @Id
    @Column(name = "partecipante_id")
    private Long participantId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partecipante_id", nullable = false)
    private Participant participant;

    @Column(name = "accesso_sala_vip", nullable = false)
    private boolean vipRoomAccess;

    @Column(name = "risposte_wordcloud", nullable = false)
    private int wordcloudAnswers;

    public VipRoomInteraction(Participant participant, boolean vipRoomAccess, int wordcloudAnswers) {
        this.participant = participant;
        this.vipRoomAccess = vipRoomAccess;
        this.wordcloudAnswers = wordcloudAnswers;
    }
}
