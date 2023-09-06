package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.UserRole;

@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class Role {
        int id;
        UserRole userRole;
}
