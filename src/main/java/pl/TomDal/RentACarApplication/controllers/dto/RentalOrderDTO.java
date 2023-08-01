package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.domain.Customer;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalOrderDTO {
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
