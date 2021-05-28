package model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor

public class BalanceRequest {

    private int accountId;
    private BigDecimal balance;

}
