package model;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor

public class Client {

    private int clientId;
    private String name;
    private String phoneNumber;
    private String email;
    private String passportNumber;

}
