package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.entity.CustomerEntity;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    Integer addressId;
    String country;
    String city;
    String postalCode;
    String address;
}
