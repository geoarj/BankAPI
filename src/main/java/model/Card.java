package model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor

public class Card {

    private int cardId;
    private long cardNumber;
    private int accountId;

    public Card(int cardId, long cardNumber) {
    }
}
