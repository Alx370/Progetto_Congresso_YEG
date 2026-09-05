package it.formicola.congress_be.application.views.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandInteractionItem {

    private boolean standVisited;
    private LocalDate visitDate;
    private int views;
    private int scroll;
}
