package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.Role;
import pl.TomDal.RentACarApplication.entity.RoleEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {

    RoleEntity mapToEntity(Role role);

    Role mapFromEntity(RoleEntity roleentity);
}
