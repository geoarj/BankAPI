package model;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor

public class Account {

    private int accountId;
    private long accountNumber;
    private BigDecimal balance;
    private int clientId;


}
