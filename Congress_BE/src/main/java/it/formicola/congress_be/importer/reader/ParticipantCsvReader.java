package it.formicola.congress_be.importer.reader;

import it.formicola.congress_be.importer.model.ParticipantCsvData;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ParticipantCsvReader {

    private static final List<String> HEADERS = List.of(
            "ID",
            "Nome e cognome",
            "Email (chiave)",
            "Tipologia stakeholder",
            "Regione",
            "Canale di ingaggio",
            "In database DEM"
    );

    private final CsvFileReader csvFileReader;

    public ParticipantCsvReader(CsvFileReader csvFileReader) {
        this.csvFileReader = csvFileReader;
    }

    public List<ParticipantCsvData> read(Path filePath) {
        List<ParticipantCsvData> participants = new ArrayList<>();

        for (CsvFileReader.CsvRow row : csvFileReader.read(filePath, HEADERS)) {
            participants.add(readParticipant(row));
        }

        return participants;
    }

    private ParticipantCsvData readParticipant(CsvFileReader.CsvRow row) {
        long excelId;
        try {
            excelId = Long.parseLong(text(row, 0));
        } catch (NumberFormatException exception) {
            throw CsvFileReader.rowError(row.lineNumber(), "ID deve essere un numero intero");
        }

        return new ParticipantCsvData(
                excelId,
                text(row, 1),
                text(row, 2).toLowerCase(Locale.ROOT),
                text(row, 3),
                text(row, 4),
                text(row, 5),
                booleanValue(row, 6)
        );
    }

    private String text(CsvFileReader.CsvRow row, int index) {
        String value = row.columns().get(index).trim().replaceAll("\\s+", " ");
        if (value.isBlank()) {
            throw CsvFileReader.rowError(
                    row.lineNumber(),
                    "la colonna '" + HEADERS.get(index) + "' e vuota"
            );
        }
        return value;
    }

    private boolean booleanValue(CsvFileReader.CsvRow row, int index) {
        return switch (text(row, index).toLowerCase(Locale.ROOT)) {
            case "1", "si", "sì", "true", "vero" -> true;
            case "0", "no", "false", "falso" -> false;
            default -> throw CsvFileReader.rowError(
                    row.lineNumber(),
                    "valore booleano non valido"
            );
        };
    }
}
