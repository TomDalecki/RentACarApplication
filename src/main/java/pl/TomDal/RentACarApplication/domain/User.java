package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.RoleEntity;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class User {
    Integer userId;
    String email;
    String password;
    Boolean active;
    Set<RoleEntity> roles;
}
