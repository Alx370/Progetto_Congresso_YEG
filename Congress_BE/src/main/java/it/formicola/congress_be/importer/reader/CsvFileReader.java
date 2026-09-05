package it.formicola.congress_be.importer.reader;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvFileReader {

    public List<CsvRow> read(Path filePath, List<String> expectedHeaders) {
        if (!Files.isRegularFile(filePath)) {
            throw new IllegalArgumentException(
                    "File CSV non trovato: " + filePath.toAbsolutePath()
            );
        }

        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            if (lines.isEmpty()) {
                throw new IllegalArgumentException("Il file CSV e vuoto");
            }

            List<String> headers = split(lines.getFirst());
            if (!headers.equals(expectedHeaders)) {
                throw new IllegalArgumentException(
                        "Intestazioni CSV non valide. Attese: " + expectedHeaders
                );
            }

            List<CsvRow> rows = new ArrayList<>();
            for (int index = 1; index < lines.size(); index++) {
                if (!lines.get(index).isBlank()) {
                    List<String> columns = split(lines.get(index));
                    if (columns.size() != expectedHeaders.size()) {
                        throw rowError(index + 1, "numero di colonne non valido");
                    }
                    rows.add(new CsvRow(index + 1, columns));
                }
            }
            return rows;
        } catch (IOException exception) {
            throw new IllegalStateException("Errore durante la lettura del CSV", exception);
        }
    }

    private List<String> split(String line) {
        return Arrays.asList(line.split(";", -1));
    }

    public static IllegalArgumentException rowError(int lineNumber, String detail) {
        return new IllegalArgumentException(
                "Errore alla riga CSV " + lineNumber + ": " + detail
        );
    }

    public record CsvRow(int lineNumber, List<String> columns) {
    }
}
