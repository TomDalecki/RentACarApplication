package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.CustomerEntity;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "country", "city", "postalCode", "address"})
public class Address {

    Integer addressId;
    String country;
    String city;
    String postalCode;
    String address;
    Set<CustomerEntity> customers;
}
