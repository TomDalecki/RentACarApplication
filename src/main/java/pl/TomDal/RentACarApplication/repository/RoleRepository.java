package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.Role;
import pl.TomDal.RentACarApplication.repository.jpa.RoleJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.RoleEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.RoleDAO;

@Repository
@AllArgsConstructor
public class RoleRepository implements RoleDAO {
    RoleJpaRepository roleJpaRepository;
    RoleEntityMapper roleEntityMapper;

    @Override
    public Role createRole(Role role) {
        return roleEntityMapper.mapFromEntity(roleJpaRepository.save(roleEntityMapper.mapToEntity(role)));
    }
}
