package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerDTO;
import pl.TomDal.RentACarApplication.controllers.dto.RentalOrderDTO;
import pl.TomDal.RentACarApplication.domain.Customer;
import pl.TomDal.RentACarApplication.domain.RentalOrder;

@Mapper(componentModel = "spring")
public interface RentalOrderMapper {

    RentalOrderDTO mapToDTO(final RentalOrder rentalOrder);
}
