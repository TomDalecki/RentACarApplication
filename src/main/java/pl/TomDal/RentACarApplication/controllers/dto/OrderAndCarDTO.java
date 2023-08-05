package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderAndCarDTO {
    Integer carToRentId;
    Integer rentalOrderId;
    String carIdNumber;
    CarType carType;
    String brand;
    String model;
    Integer year;
    String color;
    LocalDate rentalStartDate;
    LocalDate rentalEndDate;
    BigDecimal totalPrice;

//    String vin;
//    String rentNumber;
//    OffsetDateTime receivedDateTime;

//    OrderStatus orderStatus;
//    Customer customer;
//    Employee employee;
}
