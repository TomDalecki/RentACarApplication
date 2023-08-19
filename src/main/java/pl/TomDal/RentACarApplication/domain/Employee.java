package pl.TomDal.RentACarApplication.domain;

import lombok.*;

import java.util.Set;


@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class Employee {
    Integer employeeId;
    String name;
    String surname;
    String pesel;
    String email;
    Set<RentalOrder> rentalOrders;
}
