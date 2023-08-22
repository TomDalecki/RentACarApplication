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


    @Mapping(source = "rentalOrders", target = "rentalOrders", qualifiedByName = "mapRentalOrders")
    Employee mapFromEntity(EmployeeEntity entity);

    @Named("mapRentalOrders")
    @SuppressWarnings("unused")
    default Set<RentalOrder> mapRentalOrders(Set<RentalOrderEntity> rentalOrderEntities) {
        return rentalOrderEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }

    @Mapping(target = "carToRent", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "employee", ignore = true)
    RentalOrder mapFromEntity(RentalOrderEntity entity);

    EmployeeEntity mapToEntity(Employee employee);
}
