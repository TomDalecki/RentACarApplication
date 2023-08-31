package pl.TomDal.RentACarApplication.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.CarToRentEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarToRentEntityMapper {
    @Mapping(target = "rentalOrders", ignore = true)
    CarToRent mapFromEntity(CarToRentEntity entity);
    CarToRentEntity mapToEntity(CarToRent car);

}
