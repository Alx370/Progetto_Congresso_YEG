package it.formicola.congress_be.application.business.internal.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "partecipanti")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_excel", nullable = false, unique = true)
    private Long excelId;

    @Column(name = "nome_cognome", nullable = false, length = 255)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipologia_stakeholder_id", nullable = false)
    private StakeholderType stakeholderType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regione_id", nullable = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "canale_ingaggio_id", nullable = false)
    private EngagementChannel engagementChannel;

    @Column(name = "in_database_dem", nullable = false)
    private boolean inDemDatabase;

    @CreationTimestamp
    @Column(name = "creato_il", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "aggiornato_il", nullable = false)
    private LocalDateTime updatedAt;

    public Participant(Long excelId, String fullName, String email, StakeholderType stakeholderType, Region region, EngagementChannel engagementChannel, boolean inDemDatabase) {
        this.excelId = excelId;
        this.fullName = fullName;
        this.email = email;
        this.stakeholderType = stakeholderType;
        this.region = region;
        this.engagementChannel = engagementChannel;
        this.inDemDatabase = inDemDatabase;
    }
}
