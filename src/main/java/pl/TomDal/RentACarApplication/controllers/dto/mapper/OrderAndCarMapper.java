package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.TomDal.RentACarApplication.controllers.dto.OrderAndCarDTO;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderAndCarMapper {

    OrderAndCarDTO mapToDTO(final OrderAndCar orderAndCar);
}
