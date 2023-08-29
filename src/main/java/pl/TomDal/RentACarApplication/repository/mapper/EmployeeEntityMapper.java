package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.EmployeeEntity;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {
    @Mapping(target = "rentalOrders", ignore = true)
    Employee mapFromEntity(EmployeeEntity entity);

    EmployeeEntity mapToEntity(Employee employee);
}
