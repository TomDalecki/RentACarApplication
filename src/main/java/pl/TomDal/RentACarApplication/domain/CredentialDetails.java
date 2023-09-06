package pl.TomDal.RentACarApplication.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class CredentialDetails {
    String email;
    String password;
}
