package it.formicola.congress_be.application.views.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymposiumInteractionItem {

    private boolean symposiumAttendance;
    private Integer durationMinutes;
    private BigDecimal focusRate;
    private int completedQuizzes;
}
