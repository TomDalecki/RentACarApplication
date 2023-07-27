package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RentalOrderEntityMapper {

    @Mapping(target = "carsToRent", ignore = true)
    @Mapping(target = "employee", ignore = true)
    RentalOrder mapFromEntity(RentalOrderEntity rentalOrderEntity);

    @Mapping(target = "carsToRent", ignore = true)
    @Mapping(target = "employee", ignore = true)
    RentalOrderEntity mapToEntity(RentalOrder rentalOrder);
}
