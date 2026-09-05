package it.formicola.congress_be.importer.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InteractionCsvData(
        String email,
        boolean demSent,
        boolean demDelivered,
        boolean demOpened,
        boolean linkedinAdReach,
        boolean linkedinAdInteraction,
        boolean linkedinRecapReach,
        boolean linkedinRecapInteraction,
        boolean standVisited,
        LocalDate visitDate,
        int views,
        int scroll,
        boolean vipRoomAccess,
        int wordcloudAnswers,
        boolean symposiumAttendance,
        Integer durationMinutes,
        BigDecimal focusRate,
        int completedQuizzes
) {
}
