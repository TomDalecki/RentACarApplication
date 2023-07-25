package pl.TomDal.RentACarApplication.domain;

import lombok.*;

import java.util.Set;


@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class Salesman {
    Integer salesmanId;
    String name;
    String surname;
    String pesel;
    Set<RentalOrder> rentalOrders;
}
