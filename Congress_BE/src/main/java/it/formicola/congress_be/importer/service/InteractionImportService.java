package it.formicola.congress_be.importer.service;

import it.formicola.congress_be.importer.model.ImportOutcome;
import it.formicola.congress_be.importer.model.ImportReport;
import it.formicola.congress_be.importer.model.InteractionCsvData;
import it.formicola.congress_be.importer.reader.InteractionCsvReader;
import it.formicola.congress_be.importer.repository.InteractionImportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;

@Service
public class InteractionImportService {

    private final InteractionCsvReader csvReader;
    private final InteractionImportRepository interactionRepository;

    public InteractionImportService(
            InteractionCsvReader csvReader,
            InteractionImportRepository interactionRepository
    ) {
        this.csvReader = csvReader;
        this.interactionRepository = interactionRepository;
    }

    @Transactional
    public ImportReport importCsv(Path filePath) {
        int inserted = 0;
        int updated = 0;
        int unchanged = 0;

        for (InteractionCsvData interaction : csvReader.read(filePath)) {
            ImportOutcome outcome = interactionRepository.save(interaction);

            switch (outcome) {
                case INSERTED -> inserted++;
                case UPDATED -> updated++;
                case UNCHANGED -> unchanged++;
            }
        }

        return new ImportReport(inserted, updated, unchanged);
    }
}
