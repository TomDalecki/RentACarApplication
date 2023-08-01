package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class RentalOrder {
    Integer rentalOrderId;
    String rentNumber;
    OffsetDateTime receivedDateTime;
    LocalDate rentalStartDate;
    LocalDate rentalEndDate;
    BigDecimal totalPrice;
    OrderStatus orderStatus;
    Customer customer;
    CarToRent carToRent;
    Employee employee;

}
