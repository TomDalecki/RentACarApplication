package pl.TomDal.RentACarApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.entity.enums.UserRole;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "security_role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

//    @ManyToMany(mappedBy = "roles")
//    private Set<UserEntity> userEntities;
}
