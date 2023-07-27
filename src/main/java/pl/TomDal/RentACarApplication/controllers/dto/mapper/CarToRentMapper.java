package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.domain.CarToRent;

@Mapper(componentModel = "spring")
public interface CarToRentMapper {

    CarToRentDTO mapToDTO(final CarToRent carToRent);
}
