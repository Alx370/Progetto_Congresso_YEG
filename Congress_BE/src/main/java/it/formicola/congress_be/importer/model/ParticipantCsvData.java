package it.formicola.congress_be.importer.model;

public record ParticipantCsvData(
        long excelId,
        String fullName,
        String email,
        String stakeholderType,
        String region,
        String engagementChannel,
        boolean inDemDatabase
) {
}
