package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.domain.Address;

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
