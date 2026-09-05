package it.formicola.congress_be.importer.runner;

import it.formicola.congress_be.importer.model.ImportReport;
import it.formicola.congress_be.importer.service.InteractionImportService;
import it.formicola.congress_be.importer.service.ParticipantImportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@ConditionalOnProperty(prefix = "app.import", name = "enabled", havingValue = "true")
public class ImportRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(ImportRunner.class);

    private final ParticipantImportService participantService;
    private final InteractionImportService interactionService;
    private final Path participantsCsv;
    private final Path interactionsCsv;

    public ImportRunner(
            ParticipantImportService participantService,
            InteractionImportService interactionService,
            @Value("${app.import.partecipanti-file}") String participantsCsv,
            @Value("${app.import.interazioni-file}") String interactionsCsv
    ) {
        this.participantService = participantService;
        this.interactionService = interactionService;
        this.participantsCsv = Path.of(participantsCsv);
        this.interactionsCsv = Path.of(interactionsCsv);
    }

    @Override
    public void run(ApplicationArguments args) {
        ImportReport participants = participantService.importCsv(participantsCsv);
        ImportReport interactions = interactionService.importCsv(interactionsCsv);

        log.info(
                "Partecipanti: {} inseriti, {} aggiornati, {} invariati",
                participants.inserted(), participants.updated(), participants.unchanged()
        );
        log.info(
                "Interazioni: {} inserite, {} aggiornate, {} invariate",
                interactions.inserted(), interactions.updated(), interactions.unchanged()
        );
    }
}
