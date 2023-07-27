package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.*;
import pl.TomDal.RentACarApplication.domain.Address;
import pl.TomDal.RentACarApplication.domain.RentalOrder;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    Integer customerId;
    String name;
    String surname;
    String phone;
    String email;
    Address address;
}
