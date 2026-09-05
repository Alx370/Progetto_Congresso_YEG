package it.formicola.congress_be.importer.repository;

import it.formicola.congress_be.importer.model.ImportOutcome;
import it.formicola.congress_be.importer.model.InteractionCsvData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Repository
public class InteractionImportRepository {

    private final JdbcTemplate database;

    public InteractionImportRepository(JdbcTemplate database) {
        this.database = database;
    }

    public ImportOutcome save(InteractionCsvData csvData) {
        long participantId = findParticipant(csvData.email());
        InteractionCsvData existingData = find(participantId);

        if (existingData == null) {
            insert(participantId, csvData);
            return ImportOutcome.INSERTED;
        }

        if (!existingData.equals(csvData)) {
            delete(participantId);
            insert(participantId, csvData);
            return ImportOutcome.UPDATED;
        }

        return ImportOutcome.UNCHANGED;
    }

    private long findParticipant(String email) {
        List<Long> foundIds = database.queryForList(
                "SELECT id FROM partecipanti WHERE email = ?",
                Long.class,
                email
        );

        if (foundIds.isEmpty()) {
            throw new IllegalArgumentException("Partecipante non trovato per l'email: " + email);
        }

        return foundIds.getFirst();
    }

    private InteractionCsvData find(long participantId) {
        List<InteractionCsvData> results = database.query("""
                        SELECT p.email,
                               e.dem_inviata, e.dem_consegnata, e.dem_aperta,
                               l.annuncio_reach, l.annuncio_interazione,
                               l.recap_reach, l.recap_interazione,
                               s.visita_stand, s.giorno_visita,
                               s.visualizzazioni, s.scroll,
                               v.accesso_sala_vip, v.risposte_wordcloud,
                               m.presenza_simposio, m.permanenza_min,
                               m.focus_rate, m.quiz_completati
                        FROM partecipanti p
                        JOIN interazioni_email e ON e.partecipante_id = p.id
                        JOIN interazioni_linkedin l ON l.partecipante_id = p.id
                        JOIN interazioni_stand s ON s.partecipante_id = p.id
                        JOIN interazioni_sala_vip v ON v.partecipante_id = p.id
                        JOIN interazioni_simposio m ON m.partecipante_id = p.id
                        WHERE p.id = ?
                        """,
                (result, rowNumber) -> new InteractionCsvData(
                        result.getString("email"),
                        result.getBoolean("dem_inviata"),
                        result.getBoolean("dem_consegnata"),
                        result.getBoolean("dem_aperta"),
                        result.getBoolean("annuncio_reach"),
                        result.getBoolean("annuncio_interazione"),
                        result.getBoolean("recap_reach"),
                        result.getBoolean("recap_interazione"),
                        result.getBoolean("visita_stand"),
                        result.getObject("giorno_visita", LocalDate.class),
                        result.getInt("visualizzazioni"),
                        result.getInt("scroll"),
                        result.getBoolean("accesso_sala_vip"),
                        result.getInt("risposte_wordcloud"),
                        result.getBoolean("presenza_simposio"),
                        result.getObject("permanenza_min", Integer.class),
                        normalizeDecimal(result.getBigDecimal("focus_rate")),
                        result.getInt("quiz_completati")
                ),
                participantId
        );

        return results.isEmpty() ? null : results.getFirst();
    }

    private BigDecimal normalizeDecimal(BigDecimal value) {
        return value == null ? null : value.setScale(4, RoundingMode.HALF_UP);
    }

    private void insert(long participantId, InteractionCsvData data) {
        database.update("""
                        INSERT INTO interazioni_email (
                            partecipante_id, dem_inviata, dem_consegnata, dem_aperta
                        ) VALUES (?, ?, ?, ?)
                        """,
                participantId, data.demSent(), data.demDelivered(), data.demOpened()
        );

        database.update("""
                        INSERT INTO interazioni_linkedin (
                            partecipante_id, annuncio_reach, annuncio_interazione,
                            recap_reach, recap_interazione
                        ) VALUES (?, ?, ?, ?, ?)
                        """,
                participantId,
                data.linkedinAdReach(),
                data.linkedinAdInteraction(),
                data.linkedinRecapReach(),
                data.linkedinRecapInteraction()
        );

        database.update("""
                        INSERT INTO interazioni_stand (
                            partecipante_id, visita_stand, giorno_visita,
                            visualizzazioni, scroll
                        ) VALUES (?, ?, ?, ?, ?)
                        """,
                participantId,
                data.standVisited(),
                data.visitDate(),
                data.views(),
                data.scroll()
        );

        database.update("""
                        INSERT INTO interazioni_sala_vip (
                            partecipante_id, accesso_sala_vip, risposte_wordcloud
                        ) VALUES (?, ?, ?)
                        """,
                participantId, data.vipRoomAccess(), data.wordcloudAnswers()
        );

        database.update("""
                        INSERT INTO interazioni_simposio (
                            partecipante_id, presenza_simposio, permanenza_min,
                            focus_rate, quiz_completati
                        ) VALUES (?, ?, ?, ?, ?)
                        """,
                participantId,
                data.symposiumAttendance(),
                data.durationMinutes(),
                data.focusRate(),
                data.completedQuizzes()
        );
    }

    private void delete(long participantId) {
        database.update("DELETE FROM interazioni_email WHERE partecipante_id = ?", participantId);
        database.update("DELETE FROM interazioni_linkedin WHERE partecipante_id = ?", participantId);
        database.update("DELETE FROM interazioni_stand WHERE partecipante_id = ?", participantId);
        database.update("DELETE FROM interazioni_sala_vip WHERE partecipante_id = ?", participantId);
        database.update("DELETE FROM interazioni_simposio WHERE partecipante_id = ?", participantId);
    }
}
