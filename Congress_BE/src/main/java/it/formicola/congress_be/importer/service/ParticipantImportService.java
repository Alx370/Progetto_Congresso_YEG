package it.formicola.congress_be.importer.service;

import it.formicola.congress_be.importer.model.ImportOutcome;
import it.formicola.congress_be.importer.model.ImportReport;
import it.formicola.congress_be.importer.model.ParticipantCsvData;
import it.formicola.congress_be.importer.reader.ParticipantCsvReader;
import it.formicola.congress_be.importer.repository.ParticipantImportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;

@Service
public class ParticipantImportService {

    private final ParticipantCsvReader csvReader;
    private final ParticipantImportRepository participantRepository;

    public ParticipantImportService(
            ParticipantCsvReader csvReader,
            ParticipantImportRepository participantRepository
    ) {
        this.csvReader = csvReader;
        this.participantRepository = participantRepository;
    }

    @Transactional
    public ImportReport importCsv(Path filePath) {
        int inserted = 0;
        int updated = 0;
        int unchanged = 0;

        for (ParticipantCsvData participant : csvReader.read(filePath)) {
            ImportOutcome outcome = participantRepository.save(participant);

            switch (outcome) {
                case INSERTED -> inserted++;
                case UPDATED -> updated++;
                case UNCHANGED -> unchanged++;
            }
        }

        return new ImportReport(inserted, updated, unchanged);
    }
}
