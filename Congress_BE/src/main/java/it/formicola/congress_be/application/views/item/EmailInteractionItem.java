package it.formicola.congress_be.application.views.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailInteractionItem {

    private boolean demSent;
    private boolean demDelivered;
    private boolean demOpened;
}
