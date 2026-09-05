package it.formicola.congress_be.importer.repository;

import it.formicola.congress_be.importer.model.ImportOutcome;
import it.formicola.congress_be.importer.model.ParticipantCsvData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipantImportRepository {

    private final JdbcTemplate database;

    public ParticipantImportRepository(JdbcTemplate database) {
        this.database = database;
    }

    public ImportOutcome save(ParticipantCsvData csvData) {
        ParticipantDatabaseData newData = new ParticipantDatabaseData(
                csvData.excelId(),
                csvData.fullName(),
                csvData.email(),
                findOrCreate("tipologie_stakeholder", csvData.stakeholderType()),
                findOrCreate("regioni", csvData.region()),
                findOrCreate("canali_ingaggio", csvData.engagementChannel()),
                csvData.inDemDatabase()
        );

        ParticipantDatabaseData existingData = findByEmail(csvData.email());

        if (existingData == null) {
            insert(newData);
            return ImportOutcome.INSERTED;
        }

        if (!existingData.equals(newData)) {
            update(newData);
            return ImportOutcome.UPDATED;
        }

        return ImportOutcome.UNCHANGED;
    }

    private long findOrCreate(String table, String name) {
        List<Long> foundIds = database.queryForList(
                "SELECT id FROM " + table + " WHERE nome = ?",
                Long.class,
                name
        );

        if (!foundIds.isEmpty()) {
            return foundIds.getFirst();
        }

        database.update("INSERT INTO " + table + " (nome) VALUES (?)", name);

        Long newId = database.queryForObject(
                "SELECT id FROM " + table + " WHERE nome = ?",
                Long.class,
                name
        );

        if (newId == null) {
            throw new IllegalStateException("Impossibile recuperare il valore: " + name);
        }

        return newId;
    }

    private ParticipantDatabaseData findByEmail(String email) {
        List<ParticipantDatabaseData> results = database.query("""
                        SELECT id_excel, nome_cognome, email,
                               tipologia_stakeholder_id, regione_id,
                               canale_ingaggio_id, in_database_dem
                        FROM partecipanti
                        WHERE email = ?
                        """,
                (result, rowNumber) -> new ParticipantDatabaseData(
                        result.getLong("id_excel"),
                        result.getString("nome_cognome"),
                        result.getString("email"),
                        result.getLong("tipologia_stakeholder_id"),
                        result.getLong("regione_id"),
                        result.getLong("canale_ingaggio_id"),
                        result.getBoolean("in_database_dem")
                ),
                email
        );

        return results.isEmpty() ? null : results.getFirst();
    }

    private void insert(ParticipantDatabaseData data) {
        database.update("""
                        INSERT INTO partecipanti (
                            id_excel, nome_cognome, email,
                            tipologia_stakeholder_id, regione_id,
                            canale_ingaggio_id, in_database_dem
                        ) VALUES (?, ?, ?, ?, ?, ?, ?)
                        """,
                data.excelId(),
                data.fullName(),
                data.email(),
                data.stakeholderTypeId(),
                data.regionId(),
                data.engagementChannelId(),
                data.inDemDatabase()
        );
    }

    private void update(ParticipantDatabaseData data) {
        database.update("""
                        UPDATE partecipanti
                        SET id_excel = ?, nome_cognome = ?,
                            tipologia_stakeholder_id = ?, regione_id = ?,
                            canale_ingaggio_id = ?, in_database_dem = ?,
                            aggiornato_il = CURRENT_TIMESTAMP
                        WHERE email = ?
                        """,
                data.excelId(),
                data.fullName(),
                data.stakeholderTypeId(),
                data.regionId(),
                data.engagementChannelId(),
                data.inDemDatabase(),
                data.email()
        );
    }

    private record ParticipantDatabaseData(
            long excelId,
            String fullName,
            String email,
            long stakeholderTypeId,
            long regionId,
            long engagementChannelId,
            boolean inDemDatabase
    ) {
    }
}
