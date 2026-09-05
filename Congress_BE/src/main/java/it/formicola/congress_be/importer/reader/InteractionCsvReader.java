package it.formicola.congress_be.importer.reader;

import it.formicola.congress_be.importer.model.InteractionCsvData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class InteractionCsvReader {

    private static final List<String> HEADERS = List.of(
            "email",
            "dem_inviata",
            "dem_consegnata",
            "dem_aperta",
            "li_annuncio_reach",
            "li_annuncio_interazione",
            "li_recap_reach",
            "li_recap_interazione",
            "visita_stand",
            "giorno_visita",
            "visualizzazioni",
            "scroll",
            "accesso_sala_vip",
            "risposte_wordcloud",
            "presenza_simposio",
            "permanenza_min",
            "focus_rate",
            "quiz_completati"
    );

    private final CsvFileReader csvFileReader;

    public InteractionCsvReader(CsvFileReader csvFileReader) {
        this.csvFileReader = csvFileReader;
    }

    public List<InteractionCsvData> read(Path filePath) {
        List<InteractionCsvData> interactions = new ArrayList<>();

        for (CsvFileReader.CsvRow row : csvFileReader.read(filePath, HEADERS)) {
            interactions.add(readInteraction(row));
        }

        return interactions;
    }

    private InteractionCsvData readInteraction(CsvFileReader.CsvRow row) {
        return new InteractionCsvData(
                text(row, 0),
                booleanValue(row, 1),
                booleanValue(row, 2),
                booleanValue(row, 3),
                booleanValue(row, 4),
                booleanValue(row, 5),
                booleanValue(row, 6),
                booleanValue(row, 7),
                booleanValue(row, 8),
                optionalDate(row, 9),
                integer(row, 10),
                integer(row, 11),
                booleanValue(row, 12),
                integer(row, 13),
                booleanValue(row, 14),
                optionalInteger(row, 15),
                optionalDecimal(row, 16),
                integer(row, 17)
        );
    }

    private String text(CsvFileReader.CsvRow row, int index) {
        String value = row.columns().get(index).trim();
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
            case "1", "true", "vero", "si", "sì" -> true;
            case "0", "false", "falso", "no" -> false;
            default -> throw CsvFileReader.rowError(
                    row.lineNumber(),
                    "valore booleano non valido in '" + HEADERS.get(index) + "'"
            );
        };
    }

    private int integer(CsvFileReader.CsvRow row, int index) {
        try {
            return Integer.parseInt(text(row, index));
        } catch (NumberFormatException exception) {
            throw CsvFileReader.rowError(
                    row.lineNumber(),
                    "numero intero non valido in '" + HEADERS.get(index) + "'"
            );
        }
    }

    private Integer optionalInteger(CsvFileReader.CsvRow row, int index) {
        if (row.columns().get(index).isBlank()) {
            return null;
        }
        return integer(row, index);
    }

    private BigDecimal optionalDecimal(CsvFileReader.CsvRow row, int index) {
        if (row.columns().get(index).isBlank()) {
            return null;
        }
        try {
            return new BigDecimal(text(row, index)).setScale(4, RoundingMode.HALF_UP);
        } catch (NumberFormatException exception) {
            throw CsvFileReader.rowError(
                    row.lineNumber(),
                    "numero decimale non valido in '" + HEADERS.get(index) + "'"
            );
        }
    }

    private LocalDate optionalDate(CsvFileReader.CsvRow row, int index) {
        if (row.columns().get(index).isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(text(row, index));
        } catch (DateTimeParseException exception) {
            throw CsvFileReader.rowError(
                    row.lineNumber(),
                    "data non valida in '" + HEADERS.get(index) + "'"
            );
        }
    }
}
