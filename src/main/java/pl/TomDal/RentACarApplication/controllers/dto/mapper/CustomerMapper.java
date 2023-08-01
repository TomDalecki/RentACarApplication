package pl.TomDal.RentACarApplication.controllers.dto.mapper;

import org.mapstruct.Mapper;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerDTO;
import pl.TomDal.RentACarApplication.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO mapToDTO(final Customer customer);
}
