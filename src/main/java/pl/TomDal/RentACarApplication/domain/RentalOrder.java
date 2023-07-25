package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class RentalOrder {
    Integer rentalOrderId;
    String rentNumber;
    OffsetDateTime receivedDateTime;
    OffsetDateTime rentalStartDate;
    OffsetDateTime rentalEndDate;
    BigDecimal totalPrice;
    OrderStatus orderStatus;
    Customer customer;
    Set<CarToRent> carsToRent;
    Salesman salesman;

}
