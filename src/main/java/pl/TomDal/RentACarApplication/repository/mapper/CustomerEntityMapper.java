package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.Customer;
import pl.TomDal.RentACarApplication.entity.CustomerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerEntityMapper {
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "rentalOrders", ignore = true)
    Customer mapFromEntity(CustomerEntity entity);

    @Mapping(target = "address", ignore = true)
    @Mapping(target = "rentalOrderEntities", ignore = true)
    CustomerEntity mapToEntity(Customer customer);
}
