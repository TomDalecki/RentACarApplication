package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.domain.CarToRent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarToRentMapper {

    CarToRentDTO mapToDTO(final CarToRent carToRent);

    CarToRent mapFromDTO(final CarToRentDTO carToRent);
}
